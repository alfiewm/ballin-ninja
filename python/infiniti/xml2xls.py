#!/usr/bin/python
#-*-coding:utf-8 -*-
import os
import os.path
import sys
import glob
from openpyxl import Workbook
from bs4 import BeautifulSoup
try:
    import xml.etree.cElementTree as ET
except ImportError:
    import xml.etree.ElementTree as ET

def get_text_or_empty(val):
    return val.text if val is not None else ""

def appendWs(ws, cjhm, je, se, jshj, kprq, fpzt, ghdw, absPath):
    date = formatDate(kprq)
    year = extractYear(date)
    month = extractMonth(date)
    if jshj is None or jshj is "":
        ws.append([year, month, ghdw, cjhm, float(je), float(se), float(je) + float(se), date, fpzt, absPath])
    else:
        ws.append([year, month, ghdw, cjhm, toFloat(je), toFloat(se), float(jshj), date, fpzt, absPath])

def toFloat(val):
    return float(val) if val is not None and val is not "" else ""

def formatDate(val):
    if val is None or val is "":
        return ""
    else:
        val = val.replace("-", "")
        return val[:4] + "/" + val[4:6] + "/" + val[6:8]

def extractYear(val):
    return val[:4] + u"年"

def extractMonth(val):
    return val[5:7] + u"月"

# business/body
def process(ws, content, absPath):
    content = content.decode('GB18030').encode('utf-8')
    content = content.replace('encoding="gbk"', 'encoding="utf-8"')
    dom = ET.fromstring(content)
    elements = dom.findall('./body')
    count = 0
    for body in elements:
        ghdw = get_text_or_empty(body.find('ghdw'))
        if ghdw is "":
            ghdw = get_text_or_empty(body.find('gfdwmc'))
        cjhm = get_text_or_empty(body.find('clsbdh'))
        if cjhm is "":
            cjhm = get_text_or_empty(body.find('cjhm'))
        je = get_text_or_empty(body.find('je'))
        se = get_text_or_empty(body.find('se'))
        jshj = get_text_or_empty(body.find('jshj'))
        kprq = get_text_or_empty(body.find('kprq'))
        fpzt = get_text_or_empty(body.find('fpzt'))
        if fpzt == "":
            fpzt = get_text_or_empty(body.find('yhzcbs'))
        if cjhm is None or len(cjhm) != 17:
            print "skiped empty invoice. ", cjhm, je, se, jshj, kprq, fpzt
            continue
        appendWs(ws, cjhm, je, se, jshj, kprq, fpzt, ghdw, absPath)
        count = count + 1
    return count

# taxML
def processTAXML(ws, content, absPath):
    content = content.decode('GB18030').encode('utf-8').replace('encoding="GBK"', 'encoding="utf-8"')
    soup = BeautifulSoup(content, "lxml")
    clsbdhList = soup.find_all("clsbdh")
    jshjList = soup.find_all("jshj")
    kprqList = soup.find_all("kprq")
    fpbzList = soup.find_all("fpbz")
    ghdwList = soup.find_all("ghdw")
    count = 0
    for (clsbdh, jshj, kprq, fpbz, ghdw) in zip(clsbdhList, jshjList, kprqList, fpbzList, ghdwList):
        if clsbdh.string is None or len(clsbdh.string) != 17:
            print "skiped empty invoice. ", clsbdh.string, jshj.string, kprq.string, fpbz.string
            continue
        appendWs(ws, clsbdh.string, "", "", jshj.string, kprq.string, fpbz.string, ghdw.string, absPath)
        count = count + 1
    return count

# lzsc.xml & tssf.xml
def processExcelSheet(ws, fileContent, absPath):
    soup = BeautifulSoup(fileContent, 'lxml')
    index = 0
    cjhmIndex = -1
    jeIndex = -1
    seIndex = -1
    kprqIndex = -1
    jshjIndex = -1
    fpztIndex = -1
    ghdwIndex = -1
    count = 0
    for item in soup.find_all('row'):
        cellList = item.find_all('cell')
        if len(cellList) < 10:
            continue
        if index == 0:
            index = 1
            for idx, cell in enumerate(cellList):
                title = cell_string(cell)
                if title == "cjhm" or title == u"车架号码" or title == "ns1:clsbdh":
                    cjhmIndex = idx
                elif title == "je" or title == u"金额" or title == "ns1:je":
                    jeIndex = idx
                elif title == "se" or title == u"税额" or title == "ns1:se":
                    seIndex = idx
                elif title == "kprq" or title == u"开票日期" or title == "ns1:kprq":
                    kprqIndex = idx
                elif title == "jshj" or title == u"价税合计" or title == "ns1:jshj":
                    jshjIndex = idx
                elif title == "fpzt" or title == u"发票状态" or title == "ns1:fpzt":
                    fpztIndex = idx
                elif title == "ghdw" or title == u"购货单位" or title == "ns1:ghdw":
                    ghdwIndex = idx
            # print cjhmIndex , " " , jeIndex , " " , seIndex , " " , kprqIndex , " " , jshjIndex , " " , fpztIndex
        else:
            cjhm = ""
            je = ""
            se = ""
            jshj = ""
            fpzt = ""
            kprq = ""
            ghdw = ""
            if cjhmIndex > -1:
                cjhm = cell_string(cellList[cjhmIndex])
            if jeIndex > -1:
                je = cell_string(cellList[jeIndex])
            if seIndex > -1:
                se = cell_string(cellList[seIndex])
            if kprqIndex > -1:
                kprq = cell_string(cellList[kprqIndex])
            if jshjIndex > -1:
                jshj = cell_string(cellList[jshjIndex])
            if fpztIndex > -1:
                fpzt = cell_string(cellList[fpztIndex])
            if ghdwIndex > -1:
                ghdw = cell_string(cellList[ghdwIndex])
            if cjhm is None or cjhm == "" or len(cjhm) != 17:
                print "skiped empty invoice. ", cjhm, je, se, jshj, kprq, fpzt, ghdw
                continue
            appendWs(ws, cjhm, je, se, jshj, kprq, fpzt, ghdw, absPath)
            count += 1
        return count

def cell_string(cell):
    return cell.text

if __name__ == '__main__':
    wb = Workbook()
    ws = wb.active
    ws.title = "xml"
    ws.append(["年", "月", "客户姓名", "车架号码", "价格", "税额", "价税合计", "开票日期", "发票状态", "路径"])
    totalCount = 0
    for root, dirs, files in os.walk("."):
        for name in files:
            absPath = root + "/" + name
            if not absPath.endswith(".xml"):
                continue
            content = open(absPath, 'rb').read()
            print "processing " + absPath
            count = 0
            # ws.append([absPath])
            if content.find("Excel.Sheet") != -1:
                count = processExcelSheet(ws, content, absPath)
            elif content.find("taxML") != -1:
                count = processTAXML(ws, content, absPath)
            else:
                count = process(ws, content, absPath)
            totalCount += count
            print " ####### added ", count, " items.", absPath
    print "------------ tatal count = " , totalCount
    wb.save('output.xlsx')
    os.system("open output.xlsx")

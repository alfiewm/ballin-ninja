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

def appendWs(ws, cjhm, je, se, jshj, kprq, fpzt):
    date = formatDate(kprq)
    year = extractYear(date)
    month = extractMonth(date)
    if jshj is None or jshj is "":
        ws.append([year, month, cjhm, float(je), float(se), float(je) + float(se), date, fpzt])
    else:
        ws.append([year, month, cjhm, toFloat(je), toFloat(se), float(jshj), date, fpzt])

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
def process(ws, content):
    content = content.decode('GB18030').encode('utf-8')
    content = content.replace('encoding="gbk"', 'encoding="utf-8"')
    dom = ET.fromstring(content)
    elements = dom.findall('./body')
    count = 0
    for body in elements:
        cjhm = get_text_or_empty(body.find('clsbdh'))
        if cjhm is "":
            cjhm = get_text_or_empty(body.find('cjhm'))
        je = get_text_or_empty(body.find('je'))
        se = get_text_or_empty(body.find('se'))
        jshj = get_text_or_empty(body.find('jshj'))
        kprq = get_text_or_empty(body.find('kprq'))
        fpzt = get_text_or_empty(body.find('fpzt'))
        if cjhm is None or len(cjhm) != 17:
            print "skiped empty invoice. ", cjhm, je, se, jshj, kprq, fpzt
            continue
        appendWs(ws, cjhm, je, se, jshj, kprq, fpzt)
        count = count + 1
    return count

# taxML
def processTAXML(ws, content):
    content = content.decode('GB18030').encode('utf-8').replace('encoding="GBK"', 'encoding="utf-8"')
    soup = BeautifulSoup(content, "lxml")
    clsbdhList = soup.find_all("clsbdh")
    jshjList = soup.find_all("jshj")
    kprqList = soup.find_all("kprq")
    count = 0
    for (clsbdh, jshj, kprq) in zip(clsbdhList, jshjList, kprqList):
        if clsbdh.string is None or len(clsbdh.string) != 17:
            print "skiped empty invoice. ", clsbdh.string, jshj.string, kprq.string
            continue
        appendWs(ws, clsbdh.string, "", "", jshj.string, kprq.string, "")
        count = count + 1
    return count

# lzsc.xml & tssf.xml
def processExcelSheet(ws, fileContent):
    soup = BeautifulSoup(fileContent, 'lxml')
    index = 0
    cjhmIndex = -1
    jeIndex = -1
    seIndex = -1
    kprqIndex = -1
    jshjIndex = -1
    fpztIndex = -1
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
            # print cjhmIndex , " " , jeIndex , " " , seIndex , " " , kprqIndex , " " , jshjIndex , " " , fpztIndex
        else:
            cjhm = ""
            je = ""
            se = ""
            jshj = ""
            fpzt = ""
            kprq = ""
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
            if cjhm is None or cjhm == "" or len(cjhm) != 17:
                print "skiped empty invoice. ", cjhm, je, se, jshj, kprq, fpzt
                continue
            appendWs(ws, cjhm, je, se, jshj, kprq, fpzt)
            count += 1
        return count

def cell_string(cell):
    return cell.text

if __name__ == '__main__':
    wb = Workbook()
    ws = wb.active
    ws.title = "xml"
    ws.append(["年", "月", "车架号码", "价格", "税额", "价税合计", "开票日期", "发票状态"])
    let totalCount = 0
    for root, dirs, files in os.walk("."):
        for name in files:
            absPath = root + "/" + name
            if not absPath.endswith(".xml"):
                continue
            content = open(absPath, 'rb').read()
            print "processing " + absPath
            # ws.append([absPath])
            if content.find("Excel.Sheet") != -1:
                count = processExcelSheet(ws, content)
            elif content.find("taxML") != -1:
                count = processTAXML(ws, content)
            else:
                count = process(ws, content)
            totalCount += count
            print "####### added " + count " items!!!"
    print "------------ tatal count = " + totalCount
    wb.save('output.xlsx')
    os.system("open output.xlsx")

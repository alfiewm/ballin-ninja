from reportlab.graphics.shapes import Drawing, String
from reportlab.graphics import renderPDF

d = Drawing(100, 100)
s = String(50, 50, 'Hello World!', textAnchor='middle')

d.add(s)

renderPDF.drawToFile(d, 'hell.pdf', 'A simple PDF file')


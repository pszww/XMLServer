package test.dom4j.xd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;


public class Demo1 {
	@org.junit.Test
	public void test() throws Exception{
		// ����saxReader����  
		SAXReader reader = new SAXReader();
		// ͨ��read������ȡһ���ļ� ת����Document����
        Document document = reader.read(new File("src/test/dom4j/xd/book.xml"));
        //��ȡ���ڵ�Ԫ�ض���  
        Element node = document.getRootElement();  
        //�������е�Ԫ�ؽڵ�  
        listNodes(node);  
        // ��ȡ�Ĵ�����Ԫ�ؽڵ��У��ӽڵ�����Ϊ��¥��Ԫ�ؽڵ㡣  
        Element element = node.element("��¥��");  
        //��ȡelement��id���Խڵ����  
        Attribute attr = element.attribute("id");  
        //ɾ������  
        element.remove(attr);  
        //����µ�����  
        element.addAttribute("name", "����");  
        // �ں�¥��Ԫ�ؽڵ�����ӳ���Ԫ�صĽڵ�  
        Element newElement = element.addElement("����");  
        newElement.setText("�峯");  
        //��ȡelement�е�����Ԫ�ؽڵ����  
        Element author = element.element("����");  
        //ɾ��Ԫ�ؽڵ�  
        boolean flag = element.remove(author);  
        //����true����ɾ���ɹ�������ʧ��  
        System.out.println(flag);  
        //���CDATA����  
        element.addCDATA("��¥�Σ���һ������С˵.");  
        // д�뵽һ���µ��ļ���  
        writer(document);  
	}

	private void writer(Document document) throws Exception{
		// ���յĸ�ʽ  
        // OutputFormat format = OutputFormat.createCompactFormat();  
        // �Ű������ĸ�ʽ  
        OutputFormat format = OutputFormat.createPrettyPrint();  
        // ���ñ���  
        format.setEncoding("UTF-8");  
        // ����XMLWriter����,ָ����д���ļ��������ʽ  
        // XMLWriter writer = new XMLWriter(new FileWriter(new  
        // File("src//a.xml")),format);  
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(  
                new FileOutputStream(new File("src//a.xml")), "UTF-8"), format);  
        // д��  
        writer.write(document);  
        // ����д��  
        writer.flush();  
        // �رղ���  
        writer.close();  
		
	}

	private void listNodes(Element node) {
		 System.out.println("��ǰ�ڵ�����ƣ���" + node.getName());  
	        // ��ȡ��ǰ�ڵ���������Խڵ�  
	        List<Attribute> list = node.attributes();  
	        // �������Խڵ�  
	        for (Attribute attr : list) {  
	            System.out.println(attr.getText() + "-----" + attr.getName()  
	                    + "---" + attr.getValue());  
	        }  
	  
	        if (!(node.getTextTrim().equals(""))) {  
	            System.out.println("�ı����ݣ�������" + node.getText());  
	        }  
	  
	        // ��ǰ�ڵ������ӽڵ������  
	        Iterator<Element> it = node.elementIterator();  
	        // ����  
	        while (it.hasNext()) {  
	            // ��ȡĳ���ӽڵ����  
	            Element e = it.next();  
	            // ���ӽڵ���б���  
	            listNodes(e);  
	        }  
	}
}

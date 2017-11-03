package test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

public class TestThread {
	public static void main(String[] args) {
		ScriptEngine se = new ScriptEngineManager().getEngineByName("JavaScript");
		/*
		String str = "7*10+1-10";
		
		Double d;
		try {
			d = (Double) se.eval(str);
			System.out.println(d+"0");
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		SolrServer sclient=new HttpSolrServer("http://172.16.2.197:8080/solr");
	}
}

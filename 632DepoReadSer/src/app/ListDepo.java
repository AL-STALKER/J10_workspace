package app;

import java.util.*;
import java.io.*;

public class ListDepo {
	ArrayList<DepoBase> list = new ArrayList<DepoBase>();
	
	public ListDepo(){ }
	
	public void init(){
		list.add(new DepoSimple(new GregorianCalendar(2012, Calendar.SEPTEMBER, 8).getTime(), 61, 2500.0, 18.0));
		list.add(new DepoMonthCapitalize(new GregorianCalendar(2012, Calendar.FEBRUARY, 1).getTime(), 181, 10_000.0, 21.0));
		list.add(new DepoSimple(new GregorianCalendar(2012, Calendar.NOVEMBER, 12).getTime(), 30, 5500.0, 15.3));
		list.add(new DepoSimple(new GregorianCalendar(2011, Calendar.DECEMBER, 18).getTime(), 370, 43_000.0, 19.56));
		list.add(new DepoMonthCapitalize(new GregorianCalendar(2012, Calendar.JULY, 12).getTime(), 91, 12_000.0, 16.0));
	}
	
	public double getPrincipal(){
		double principal = 0.0;
		int s = list.size();
		for (int i = 0; i < s; i++){
			principal += list.get(i).getSum();
		}
		return principal;
	}

	public double getPrincipalFor(){
		double principal = 0.0;
		for (DepoBase depo: list){
			principal += depo.getSum();
		}
		return principal;
	}
	
	public void remove(){
		for (Iterator<DepoBase> it = list.iterator(); it.hasNext();){ 
			if (it.next().getSum() < 10000.0) it.remove(); 
		}
	}

	
	public ArrayList<DepoBase> getList(){
		return list;
	}
	
	public void save(String fileId){
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileId));){
			for (DepoBase depo:list) out.writeObject(depo);  
		}
		catch (IOException ei){
			System.out.println(ei.getMessage());
		}
	}

	public void read(String fileId){
		list.clear();
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileId));){
			while (true){
				DepoBase depo = (DepoBase)in.readObject();
				list.add(depo);	
			}
		}
		catch(ClassNotFoundException ec){
			System.out.println("Input file " + fileId + " structure corrupted");
		}
		catch (EOFException ee){
			return;
		}
		catch (IOException ei){
			System.out.println(ei.getMessage());
		}
	}
}


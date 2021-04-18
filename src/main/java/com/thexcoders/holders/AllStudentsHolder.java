package com.thexcoders.holders;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "AllStudents")
public class AllStudentsHolder {
	@Id
	String id; // exemple = "INF2020" = GENIE+PROMO
	ArrayList<String> idsGrpA=new ArrayList<>();
	ArrayList<String> idsGrpB=new ArrayList<>();

	public AllStudentsHolder(String id, ArrayList<String> idsGrpA, ArrayList<String> idsGrpB) {
		this.id = id;
		this.idsGrpA = idsGrpA;
		this.idsGrpB = idsGrpB;
	}

	// USE THIS FUNCTION
	public boolean addNewStudent(boolean isGrpA,String id){
		if(isGrpA && !idsGrpA.contains(id)){
			idsGrpA.add(id);
			return true;
		}
		if(!isGrpA && !idsGrpB.contains(id)){
			idsGrpB.add(id);
			return true;
		}
		return false;
	}

	public AllStudentsHolder() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<String> getIdsGrpA() {
		return idsGrpA;
	}

	public void setIdsGrpA(ArrayList<String> idsGrpA) {
		this.idsGrpA = idsGrpA;
	}

	public ArrayList<String> getIdsGrpB() {
		return idsGrpB;
	}

	public void setIdsGrpB(ArrayList<String> idsGrpB) {
		this.idsGrpB = idsGrpB;
	}
}

//package org.ab.ab_solutions_task.domain.entities;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "symbols")
//public class Symbols extends BaseEntity {
//
//	private String symbolsRepresentation;
//	private String wordsRepresentation;
//	
//	public Symbols() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	
//	@Column(name = "symbol_representation")
//	public String getSymbolsRepresentation() {
//		return this.symbolsRepresentation;
//	}
//	
//	public void setSymbolsRepresentation(String symbolsRepresentation) {
//		this.symbolsRepresentation = symbolsRepresentation;
//	}
//	
//	@Column(name = "words_representation")
//	public String getWordsRepresentation() {
//		return this.wordsRepresentation;
//	}
//	
//	public void setWordsRepresentation(String wordsRepresentation) {
//		this.wordsRepresentation = wordsRepresentation;
//	}
//	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((symbolsRepresentation == null) ? 0 : symbolsRepresentation.hashCode());
//		result = prime * result + ((wordsRepresentation == null) ? 0 : wordsRepresentation.hashCode());
//		return result;
//	}
//	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Symbols other = (Symbols) obj;
//		if (symbolsRepresentation == null) {
//			if (other.symbolsRepresentation != null)
//				return false;
//		} else if (!symbolsRepresentation.equals(other.symbolsRepresentation))
//			return false;
//		if (wordsRepresentation == null) {
//			if (other.wordsRepresentation != null)
//				return false;
//		} else if (!wordsRepresentation.equals(other.wordsRepresentation))
//			return false;
//		return true;
//	}
//	
//	@Override
//	public String toString() {
//		return "Symbols [symbolsRepresentation=" + symbolsRepresentation + ", wordsRepresentation="
//				+ wordsRepresentation + "]";
//	}
//}
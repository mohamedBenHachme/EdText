package textEditor;

import java.util.ArrayList;

public class Keywords {
	private String[] languages = {".java"};
	private String[] javaKeywords = {" abstract ", " assert ", " boolean ",
            "break ", " byte ", " case ", " catch ", " char ", " class ", " const ",
            "continue", "default", "do", "double", "else", " extends ", " false ",
            "final", " finally ", " float ", " for ", " goto ", " if ", " implements ",
            "import ", " instanceof ", " int ", " System ",
            "new", "null", "package", "private", "protected", "public", " interface ",
            "long ", " native ", " return ", " short ", "static ", "strict ", "super ", " switch ",
            "synchronized", "this", "throw", "throws ", "transient ", "true ",
            "try ", "void ", "volatile ", "while ", "String "};
	private String[] brackets = {"(","{"};
	private String[] bracketsCompletion = {")","}"};
	public String[] getLanguages(){
		return languages;
	}
	public String[] getJavaKeywords(){
		return javaKeywords;
	}
	public ArrayList<String> getBrackets(){
		ArrayList<String>  list = new ArrayList<>();
		for(String s : brackets){
			list.add(s);
		}
		return list;
	}
	public ArrayList<String> getBracketsCompletion(){
		ArrayList<String>  list = new ArrayList<>();
		for(String s : bracketsCompletion){
			list.add(s);
		}
		return list;
	}

}


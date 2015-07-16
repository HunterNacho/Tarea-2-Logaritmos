package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

import patriciatree.PatriciaTree;
import patternsearchautomaton.PatternSearchAutomaton;
import suffixtree.SuffixTree;
import bookparser.BookParser;

public class Main {
		
	public static int INSERTTICKS = 100000;
	public static int FINDTICKS = 1000;
	
	public static void main(String[] args) throws IOException {
		
		PrintStream out = new PrintStream(new FileOutputStream("/home/ekauffma/Documents/2015-1/logaritmos/Tarea-2-Logaritmos/results.txt"));
		System.setOut(out);
		
		File folder = new File("/home/ekauffma/Documents/2015-1/logaritmos/Tarea-2-Logaritmos/libros");
		File[] listOfFiles = folder.listFiles();
		
		for(File bookFile : listOfFiles) {
			
			String parsedBook = BookParser.parseTextFile(bookFile.getPath());

			String[] words = parsedBook.split(" ");
			ArrayList<String> wordsToSearch = new ArrayList<String>();
			Random randWordIndex = new Random();
			for(int i = 0; i < words.length/10; i++) {
				int index = randWordIndex.nextInt(words.length);
				wordsToSearch.add(words[index]);						
			}
			
			System.out.println("Book: " + bookFile.getName());
			System.out.println("Size: " + bookFile.length()/(1024.0*1024) + "MB");
			System.out.println("Total number of words: " + words.length);
			System.out.println("Number of words to search: " + wordsToSearch.size());
//			System.out.println("Words to search:");
//			System.out.println(wordsToSearch.toString());
			
			
			//Patricia Tree 
			System.out.println("Building PatriciaTree...");
			long startBuild_time = System.currentTimeMillis(); 
			
			PatriciaTree patTree = new PatriciaTree(parsedBook);

			long endBuild_time = System.currentTimeMillis();
			long build_time = endBuild_time - startBuild_time;
			
			System.out.println("Searching words using Patricia Tree...");
			long startSearch_time = System.currentTimeMillis();
			for(int i = 0; i < wordsToSearch.size(); i++) {
				String word = wordsToSearch.get(i);
				patTree.find(word);
//				if(i%FINDTICKS == 0) System.out.println("....searching for " + i +"th word");
			}
			long endSearch_time = System.currentTimeMillis();
			long search_time = endSearch_time - startSearch_time;
			
			System.out.println("Results for Patricia Tree");
			System.out.println("Build time took: " + build_time/1000 + " seconds = " + (build_time/1000)/60 + " minutes");
			System.out.println("Search time took: " + search_time/1000 + " seconds = " + (search_time/1000)/60 + " minutes");
			
			//Suffix Tree
			SuffixTree sufTree = new SuffixTree(bookFile.getPath());
			int bookLen = SuffixTree.bookLen;
			System.out.println("Building Suffix Tree...");
			startBuild_time = System.currentTimeMillis(); 
			for(int i=0; i < bookLen; i++) {
				sufTree.insert(i);	
//				if(i%INSERTTICKS == 0) System.out.println("....inserting index " + i);
			}
			endBuild_time = System.currentTimeMillis();
			build_time = endBuild_time - startBuild_time;
			
			System.out.println("Searching words using Suffix Tree...");
			startSearch_time = System.currentTimeMillis();
			for(int i = 0; i < wordsToSearch.size(); i++) {
				String word = " " + wordsToSearch.get(i) + " ";
				sufTree.find(word);
//				if(i%FINDTICKS == 0) System.out.println("....searching for " + i +"th word");
			}
			endSearch_time = System.currentTimeMillis();
			search_time = endSearch_time - startSearch_time;
			
			System.out.println("Results for Suffix Tree");
			System.out.println("Build time took: " + build_time/1000 + " seconds = " + (build_time/1000)/60 + " minutes");
			System.out.println("Search time took: " + search_time/1000 + " seconds = " + (search_time/1000)/60 + " minutes");
			
			//Automata
			String text = " " + parsedBook + " ";
			long buildTime = 0;
			long findTime = 0;
			
			for(int i = 0; i < wordsToSearch.size(); i++) {
				
				long startBuildWord_time = System.currentTimeMillis();
				PatternSearchAutomaton pSA = new PatternSearchAutomaton(" " + wordsToSearch.get(i) + " ");
				long endBuildWord_time = System.currentTimeMillis();
				
				buildTime += (endBuildWord_time - startBuildWord_time);
				
				long startFindWord_time = System.currentTimeMillis();
				pSA.find(text);
				long endFindWord_time = System.currentTimeMillis();
				findTime += (endFindWord_time - startFindWord_time);
//				if(i%FINDTICKS == 0) System.out.println("....searching for " + i +"th word");
			}
			
			System.out.println("Results for Pattern Search Automata");
			System.out.println("Total build time took: " + buildTime/1000 + " seconds = " + (buildTime/1000)/60 + " minutes");
			System.out.println("Total search time took: " + findTime/1000 + " seconds = " + (findTime/1000)/60 + " minutes");			
			
		}
		
	}

}

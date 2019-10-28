package com.ddu.demo.java.algorithm;


// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

// CLASS BEGINS, THIS CLASS IS REQUIRED
public class ThreeKeywordSuggestions
{   Node root = new Node();
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    List<List<String> > threeKeywordSuggestions(int numreviews,
                                                List<String> repository,
                                                String customerQuery)
    {
        Trie trie = new Trie();
        // WRITE YOUR CODE HERE
        for (int i = 0; i < numreviews; i++) {
            trie.insert(repository.get(i));
        }

        List<List<String>> resList = new ArrayList<>();
        for (int i = 2; i <= customerQuery.length(); i++) {
            String prefix = customerQuery.substring(0, i);
            resList.add(trie.getAll(prefix));
        }
        return resList;
    }
    // METHOD SIGNATURE ENDS
}

class Node {
    Map<String, Node> nexts;
    public int end;
    public Node() {
        this.nexts = new HashMap<String, Node>();
        this.end = 0;
    }
}

class Trie {
    private Node root = new Node();

    public void insert(String word) {
        if (word == null) return;
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            String str = "" + word.charAt(i);
            if (node.nexts.get(str) == null) {
                node.nexts.put(str, new Node());
            }
            node = node.nexts.get(str);
        }
        node.end = 1;
    }

    public boolean exist(String prefix) {
        Node node = root;
        for (int i = 0; i < prefix.length(); i++) {
            String str = "" + prefix.charAt(i);
            if (node.nexts.get(str) == null) {
                return false;
            }
            node = node.nexts.get(str);
        }
        return true;
    }

    public List<String> getAll(String prefix) {
        List<String> list = new ArrayList<>();
        if (!exist(prefix)) {
            return list;
        }


        StringBuilder sb = new StringBuilder();
        sb.append(prefix);

        Node node = root;
        for (int i = 0; i < prefix.length(); i++) {
            node = node.nexts.get("" + prefix.charAt(i));
        }
        dfs(node, sb, list);
        return list;
    }

    private void dfs(Node root, StringBuilder sb, List<String> list) {
        if (root.end == 1) {
            list.add(sb.toString());
            if (list.size() == 3 || root.nexts.size() == 0) {
                Collections.sort(list);
                return;
            }
        }
        Node node = root;
        for (String s : node.nexts.keySet()) {
            sb.append(s);
            dfs(node.nexts.get(s), sb, list);
            sb.delete(sb.length() - 1, sb.length());
        }
    }
}

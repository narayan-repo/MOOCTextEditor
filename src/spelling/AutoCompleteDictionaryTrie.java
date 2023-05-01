package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete
 * ADT
 * 
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

	private TrieNode root;
	private int size;

	public AutoCompleteDictionaryTrie() {
		root = new TrieNode();
	}

	/**
	 * Insert a word into the trie. For the basic part of the assignment (part 2),
	 * you should convert the string to all lower case before you insert it.
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes into
	 * the trie, as described outlined in the videos for this week. It should
	 * appropriately use existing nodes in the trie, only creating new nodes when
	 * necessary. E.g. If the word "no" is already in the trie, then adding the word
	 * "now" would add only one additional node (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 *         in the dictionary.
	 */
	public boolean addWord(String word) {
		if (word == "" || isWord(word) || word.equals(null))
			return false;

		char[] arr = word.toLowerCase().toCharArray();
		TrieNode prev = root;
		TrieNode next = null;

		for (int i = 0; i < arr.length; i++) {
			next = prev.insert(arr[i]);
			if (next == null) {
				next = prev.getChild(arr[i]);
			}
			prev = next;
		}
		if (!next.endsWord()) {
			next.setEndsWord(true);
			size++;
			return true;
		}

		return false;
	}

	/**
	 * Return the number of words in the dictionary. This is NOT necessarily the
	 * same as the number of TrieNodes in the trie.
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week.
	 */
	@Override
	public boolean isWord(String s) {
		if (s.equals("") || s.equals(null) || root.getValidNextCharacters().size() == 0)
			return false;

		String sLCase = s.toLowerCase();

		TrieNode curr = root;
		char[] sArray = sLCase.toCharArray();
		for (int i = 0; i < sArray.length; i++) {
			if (curr != null && !curr.getValidNextCharacters().isEmpty()) {
				curr = curr.getChild((Character) sArray[i]);
				if (curr != null && i == sArray.length - 1) {
					return curr.endsWord();
				}
			} else
				break;
		}
		return false;
	}

	/**
	 * Return a list, in order of increasing (non-decreasing) word length,
	 * containing the numCompletions shortest legal completions of the prefix
	 * string. All legal completions must be valid words in the dictionary. If the
	 * prefix itself is a valid word, it is included in the list of returned words.
	 * 
	 * The list of completions must contain all of the shortest completions, but
	 * when there are ties, it may break them in any order. For example, if there
	 * the prefix string is "ste" and only the words "step", "stem", "stew", "steer"
	 * and "steep" are in the dictionary, when the user asks for 4 completions, the
	 * list must include "step", "stem" and "stew", but may include either the word
	 * "steer" or "steep".
	 * 
	 * If this string prefix is not in the trie, it returns an empty list.
	 * 
	 * @param prefix         The text to use at the word stem
	 * @param numCompletions The maximum number of predictions desired.
	 * @return A list containing the up to numCompletions best predictions
	 */
	@Override
	public List<String> predictCompletions(String prefix, int numCompletions) {

		List<String> completions = new LinkedList<String>();
		Queue<TrieNode> queue = new LinkedList<TrieNode>();

		TrieNode curr = root;
		char[] cArray = prefix.toLowerCase().toCharArray();

		for (int i = 0; i < cArray.length; i++) {
			if (curr != null && !curr.getValidNextCharacters().isEmpty()) {
				curr = curr.getChild(cArray[i]);
			}
		}
		if (curr == null) {
			return completions;
		}
		queue.add(curr);
		while (!queue.isEmpty() && completions.size() < numCompletions) {
			curr = queue.remove();
			if (isWord(curr.getText())) {
				completions.add(curr.getText());
			}
			TrieNode next = null;
			for (Character c : curr.getValidNextCharacters()) {
				next = curr.getChild(c);
				queue.add(next);
			}
		}
		return completions;

	}

	// For debugging
	public void printTree() {
		printNode(root);
	}

	/** Do a pre-order traversal from this node down */
	public void printNode(TrieNode curr) {
		if (curr == null)
			return;

		System.out.println(curr.getText());

		TrieNode next = null;
		for (Character c : curr.getValidNextCharacters()) {
			next = curr.getChild(c);
			printNode(next);
		}
	}

}

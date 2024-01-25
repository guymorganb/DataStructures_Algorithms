class TrieNode {
    constructor() {
        this.children = {};
        this.endOfWord = false;
    }
}

class Trie {
    constructor() {
        this.root = new TrieNode();
    }

    // Insert a word into the trie
    insert(word) {
        let current = this.root;
        for (const char of word) {
            if (!current.children[char]) {
                current.children[char] = new TrieNode();
            }
            current = current.children[char];
        }
        current.endOfWord = true;
    }

    // Find all words in trie that start with the given prefix
    findCompletions(prefix) {
        let current = this.root;
        for (const char of prefix) {
            if (!current.children[char]) {
                return []; // No completions if prefix not found
            }
            current = current.children[char];
        }
        return this._findWordsFromNode(current, prefix);
    }

    // Helper function to recursively find all words from a given node
    _findWordsFromNode(node, prefix) {
        let words = [];
        if (node.endOfWord) {
            words.push(prefix);
        }
        for (const char in node.children) {
            words = words.concat(this._findWordsFromNode(node.children[char], prefix + char));
        }
        return words;
    }
}

export default Trie
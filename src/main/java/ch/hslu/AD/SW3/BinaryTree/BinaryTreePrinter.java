package ch.hslu.AD.SW3.BinaryTree;

public abstract class BinaryTreePrinter<T extends Comparable<T>>
{
	protected abstract int getSize();
	abstract Node<T> getRoot();
	
	
	public void print()
	{
		String[] levels = new String[getSize()];
		Node<T> root = getRoot();
		if(root == null)
		{
			levels[0] = "-";
		}
		else
		{
			levels[0] = "";
			levels = this.loadLevels(root,levels, 1);
		}
		
		this.printLevels(levels);
	}
	
	private void printLevels(String[] levels) 
	{
		for(String lvl : levels)
		{
			if (lvl != null)
			{
				System.out.println(lvl);
			}
		}
	}
	
	private String[] loadLevels(Node<T> node,String[] lvlStrings, int myLevel) 
	{
		Node<T> leftChild = node.getLeftChild();
		Node<T> rightChild = node.getRightChild();
		
		if(leftChild != null)
		{
			lvlStrings = this.loadLevels(leftChild, lvlStrings, myLevel + 1);
		}
		lvlStrings = this.updateLevelStrings(lvlStrings, node.getValue().toString(), myLevel);
		if(rightChild != null)
		{
			lvlStrings = this.loadLevels(rightChild, lvlStrings, myLevel + 1);
		}
		return lvlStrings;
	}

	private String emptySpaceCharacters(int length) 
	{
		return new String(new char[length]).replace("\0", " ");
	}
	
	private String[] updateLevelStrings(String[] lvlStrings, String value, int myLevel)
	{
		String placeholder = this.emptySpaceCharacters(value.length());
		int lengthOfFirstLevel = lvlStrings[0].length();
		
		for (int i = 0; i < myLevel; i++)
		{
			lvlStrings[i] = lvlStrings[i] == null ? "" : lvlStrings[i];
			
			if(lvlStrings[i].length() < lengthOfFirstLevel)
			{
				lvlStrings[i] = lvlStrings[i] + this.emptySpaceCharacters(lengthOfFirstLevel - lvlStrings[i].length());
			}
			
			lvlStrings[i] = (i+1 == myLevel) ? lvlStrings[i] + value : lvlStrings[i] + placeholder;
		}
		
		return lvlStrings;
	}
}

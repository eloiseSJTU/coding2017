package com.github.congcongcong250.coding2017.basic;

import java.util.NoSuchElementException;

public class SLinkedList{
	
	private Node head;
	private int size;
	
	public SLinkedList(){
		head = new Node();
		size = 0;
	}
	
	public void add(Object o){
		addLast(o);
	}
	public void add(int index , Object o){
		//Check bound
		checkIndex(index);
		
		Node pre = this.find(index - 1);
		Node nx = pre.next;
		Node in = new Node();
		in.data = o;
		in.next = nx;
		pre.next = in;
		
		size++;
	}
	public Object get(int index){
		//Check bound
		checkIndex(index);
		
		return this.find(index).data;
	}
	public Object remove(int index){
		//Check bound
		checkIndex(index);
		Node pre = this.find(index - 1);
		
		Node rem = pre.next;
		Node nx = rem.next;
		pre.next = nx;
		
		Object ret = rem.data;
		rem.next = null;
		rem.data = null;
		size--;
		return ret;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node nx = head.next;
		Node in = new Node();
		in.data = o;
		head.next = in;
		in.next = nx;
		size++;
	}
	public void addLast(Object o){
		Node last = find(size - 1);
		Node in = new Node();
		last.next = in;
		in.data = o;
		
		size++;
	}
	public Object removeFirst(){
		return remove(0);
	}
	public Object removeLast(){
		return remove(size - 1);
	}
	public WIterator iterator(){
		return null;
	}
	
	private void checkIndex(int index){
		if(index >= size || index < 0){
			throw new IndexOutOfBoundsException("Index:"+index+" Size:"+size);
		}
	}

	private Node find(int index){
		Node tra = head;

		for(int i = 0; i <= index; i++){
			tra = tra.next;
		}
		
		return tra;
	}
	
	private static class Node{
		Object data;
		Node next;
		
		public Node(){
			data = null;
			next = this;
		}
		
		public Object getData(){
			return data;
		}
		
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		if(size == 0 ){
			return ;
		}
		Node ndpre = head;
		Node nd = head.next;
		Node ndnx = nd.next;
		
		nd.next = null;
		ndpre = nd;
		nd = ndnx;
		ndnx = ndnx.next;
		
		while(ndnx != null){
			nd.next = ndpre;
			ndpre = nd;
			nd = ndnx;
			ndnx = ndnx.next;
		}
		nd.next = ndpre;
		head.next = nd;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		for(int i = 0; i < size/2;i++){
			remove(0);
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length){
		checkIndex(i - 1 + length);
		
		for(int j = 0; j < length;i++){
			remove(i);
		}
	}
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public int[] getElements(SLinkedList list){
		if(list.size() == 0){
			return new int[0];
		}
		
		int lastIndex =  (int)list.get(list.size() - 1);
		if (lastIndex >= size){
			throw new IndexOutOfBoundsException("Index:"+lastIndex+" Size:"+size);
		}
		
		int[] res = new int[list.size()];
		for(int i = 0; i < list.size(); i++){
			int index = (int)list.get(i);
			if(index >= size){
				return res;
			}
			 res[i] = (int)find(index).data;
		}
		return res;
	}
	
	

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(SLinkedList list){
		
		Node nd = head;
		Node ndnx = nd.next;
		for( int i = 0 ; i < list.size(); i++){
			int v = (int)list.get(i);
			while( ndnx != null){
				if((int)ndnx.data < v){
					nd = ndnx;
					ndnx = ndnx.next;
				}else if((int)ndnx.data == v){
					ndnx = removeNext(nd,ndnx);
				}else  if((int)ndnx.data > v){
					break;
				}
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Node nd = head.next;
		Node ndnx = nd.next;
		while(ndnx != null){
			if (nd.data == ndnx.data){
				ndnx = removeNext(nd, ndnx);
			}else{
				nd = ndnx;
				ndnx = ndnx.next;
			}
		}
	}

	private Node removeNext(Node nd, Node ndnx) {
		nd.next = ndnx.next;
		ndnx.data = null;
		ndnx.next = null;
		ndnx = nd.next;
		return ndnx;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		Node nd = head;
		Node ndnx = nd.next;
		while( ndnx != null){
			int v = (int)ndnx.data;
			if( v > min && v < max){
				ndnx = removeNext(nd,ndnx);
			}else if( v >= max){
				return;
			}
			nd = ndnx;
			ndnx = ndnx.next;
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  SLinkedList intersection( SLinkedList list){
		Node nd = head;
		Node ndnx = nd.next;
		SLinkedList res = new SLinkedList();
		for( int i = 0 ; i < list.size(); i++){
			int v = (int)list.get(i);
			while( ndnx != null){
				if((int)ndnx.data < v){
					nd = ndnx;
					ndnx = ndnx.next;
				}else if((int)ndnx.data == v){
					nd = ndnx;
					ndnx = ndnx.next;
					res.add(v);
				}else  if((int)ndnx.data > v){
					break;
				}
			}
		}
		return res;
	}

}

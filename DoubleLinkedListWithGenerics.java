package org.example;

import java.lang.module.FindException;
import java.util.NoSuchElementException;

public class DoubleLinkedListWithGenerics <T>{
    Node <T> head;
    Node <T> tail;
    int size = 0;

    DoubleLinkedListWithGenerics(T ... data){
        head = new Node(data); tail = head; size++;
    }

    DoubleLinkedListWithGenerics(){}


    public void listToString(){
        if(head == null) {
            System.out.println("Лист пуст");
        }
        Node <T> currentNode = head;
        System.out.print("Лист: ");
        while (currentNode != null){
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    public void add(T data){
        Node <T> newNode = new Node<>(data);
        if(head != null) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            size++;
            return;
        }
        tail = newNode;
        head = newNode;
        size++;

    }

    public void addId(int index, T data){
        Node <T> nextNode = new Node<>(data);
        if(index == 0){
            if(head == null){
                head = nextNode;
                tail = nextNode;
                size++;
            }
            size++;
            head.prev = nextNode;
            nextNode.next = head;
            head = nextNode;
            size++;
        }
        int id = 0;
        Node <T> currentNode = head;
        while (id < index && currentNode != null){
            if (id + 1 == index){
                nextNode.next = currentNode.next;

                if(currentNode.next != null){
                    currentNode.next.prev = nextNode;
                }
                currentNode.next = nextNode;
                nextNode.prev = currentNode;
                if(nextNode.next == null){
                    tail = nextNode;
                }
                size++;
                return;
            }
            id++;
            currentNode = currentNode.next;
        }
        if (currentNode == null) add(data); size++;
    }

    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }

    public void removeId(int index) {
        if(index == 0){
            head = head.next; size--; return;
        }
        if(index == size - 1){
            tail = tail.prev;
            size--;
            return ;
        }
        int counter = 0;
        Node <T> currentNode = head;
        while (counter < index-1 && currentNode != null){
            counter ++;
            currentNode = currentNode.next;
        }

        currentNode.next = currentNode.next.next;
        currentNode.next.prev = currentNode;
        size--;
    }

    public T findId(int index){
        int counter = 0;
        Node <T>    currentNode = head;
        while(counter < index && currentNode != null){
            counter++;
            currentNode = currentNode.next;
        }
        return currentNode.data;
    }

    public int findElement(T data){
        int counter = 0;
        Node <T> currentNode = head;
        while(currentNode != null){
            if(currentNode.data == data){
                return counter;
            }
            currentNode = currentNode.next;
            counter++;
        }
        return -1;
    }
    public T get (int index){
        Node <T> current = head;
        if(index >= size){
            throw new IndexOutOfBoundsException();
        }else{
            int pos = 0;
            while (pos < index){
                current = current.next;
                pos++;
            }
            return current.data;
        }
    }

    private class MyIterator <E> {
        int cursor = 0;
        int last = -1;

        boolean hasNext() {
            return cursor != size;
        }
        T next(){
            int i = cursor;
            if (i > size) {
                throw new NoSuchElementException();
            }

            T next = get(cursor);

            last = cursor;
            cursor++;
            return next;
        }
        void remove() {
            if (last < 0){
                throw new IllegalStateException();
            }
            removeId(last);
            cursor = last;
            last = cursor - 1;

        }
    }
}

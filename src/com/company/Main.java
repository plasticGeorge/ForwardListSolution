package com.company;

public class Main {

    public static void main(String[] args) {
        ForwardList<Integer> L1 = new ForwardList<>(8, 3, 7, 11, 5, 4, 13, 1, 0, 9, 10, 2, 15);
        ForwardList<Integer> L2 = new ForwardList<>(1, 0, 16, 9);
        L2.setNext(L1.getNodeByIndex(9));
        L2.length += 4;

        int index = -1;
        boolean isFound = false;
        for(int i = L1.length - L2.length, j = 0; i < L1.length && j < L2.length; i++, j++){
            if(L1.getElementByIndex(i).intValue() == L2.getElementByIndex(j).intValue() && !isFound) {
                index = i;
                isFound = true;
            }
            else if(L1.getElementByIndex(i).intValue() != L2.getElementByIndex(j).intValue()){
                index = -1;
            }
        }

        System.out.println(index + " элемент по первому списку");

        //код конечно говно, но не вижу смысла заморачиватся с моделированием ситуации
    }
}

class ForwardList<T>{
    private Node _head;
    public int length;

    private class Node{
        public T _value;
        public Node _next;

        public Node(T value, Node next){
            _value = value;
            _next = next;
        }

        public Node(){
        }

        public T getValue(){
            return _value;
        }
    }

    //*
    public ForwardList(T ... values){
        length = values.length;
        _head = new Node();
        Node currentNode = _head;
        for(int i = 0; i < length; i++){
            currentNode._value = values[i];
            if(i != length - 1)
                currentNode._next = currentNode = new Node();
        }
    }

    public ForwardList(){
    }

    public T getElementByIndex(int index){
        if(index >= length || index < 0)
            return null;
        Node res = _head;
        for(int i = 0; i < index; i ++)
            res = res._next;
        return res._value;
    }

    //Типа значение по умолчанию(камон, почему они недобавили эту функцию по-нормальному :D)
    public T getElementByValue(T value) {
        return getElementByValue(value, 1);
    }

    public T getElementByValue(T value, int orderNumber){
        int count = 0;
        Node res = null;
        for(Node currentNode = _head; currentNode != null; currentNode = currentNode._next)
            if(currentNode._value == value) {
                count++;
                if(count == orderNumber) {
                    res = currentNode;
                    break;
                }
            }
        return res != null ? res._value : null;
    }

    public void addToStart(T value){
        if(_head == null)
            this.addFirst(value);
        Node newNode = new Node(value, _head);
        _head = newNode;
        length++;
    }

    public void addToBack(T value){
        if(_head == null)
            this.addFirst(value);
        Node lastElement;
        for(lastElement = _head; lastElement._next != null; lastElement = lastElement._next){}
        lastElement._next = new Node(value, null);
        length++;
    }

    public void insert(T value, int index){
        if(index > length || index < 0)
            return;
        if(index == 0)
            this.addToStart(value);
        if(index == length)
            this.addToBack(value);
        Node previousElement = _head;
        for(int i = 0; i < index - 1 ; i++, previousElement = previousElement._next){}
        Node newElement = new Node(value, previousElement._next);
        previousElement._next = newElement;
        length++;
    }

    private void addFirst(T value){
        Node newNode = new Node(value, null);
        _head = newNode;
        length++;
    }

    public void removeFromStart(){
        if(length == 1) {
            _head = null;
            length--;
        }
        if (_head == null)
            return;
        _head = _head._next;
        length--;
    }

    public void removeFromEnd(){
        if(length == 1){
            _head = null;
            length--;
        }
        if (_head == null)
            return;
        Node preLastElement = _head;
        for(int i = 0; i < length - 1 ; i++, preLastElement = preLastElement._next){}
        preLastElement._next = null;
        length--;
    }

    public void remove(int index){
        if(index >= length || index < 0)
            return;
        if(index == 0)
            this.removeFromStart();
        Node previousElement = _head;
        for(int i = 0; i < index - 1 ; i++, previousElement = previousElement._next){}
        previousElement._next = previousElement._next._next;
        length--;
    }


    //Эти методы используются только для теста, в обычном листе их быть не должно
    public void setNext(Node next){
        this.getLastNode()._next = next;
    }

    public Node getLastNode(){
        Node res = _head;
        for(int i = 0; i < length - 1; i++)
            res = res._next;
        return res;
    }

    public Node getNodeByIndex(int index){
        Node res = _head;
        for(int i = 0; i < index; i++)
            res = res._next;
        return res;
    }
}

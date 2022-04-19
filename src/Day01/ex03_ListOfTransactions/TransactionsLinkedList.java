package Day01.ex03_ListOfTransactions;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private final Node  Head;
    private final Node  Last;
    private int         Size;

    private static class   Node {
        public Transaction     data;
        public Node            prev;
        public Node            next;

        public Node(Transaction newTransaction, Node newPrev, Node newNext) {
            data = newTransaction;
            prev = newPrev;
            next = newNext;
        }
    }


    public                 TransactionsLinkedList() {
        this.Head = new Node(null, null, null);
        this.Last = new Node(null, null, null);
        this.Head.next = this.Last;
        this.Last.prev = this.Head;
        this.Size = 0;
    }

    @Override
    public void            addTransaction(Transaction newTransaction) {
        this.Head.next = new Node(newTransaction,  this.Head, this.Head.next);
        this.Size++;
    }

    @Override
    public void            removeByID(UUID id) throws TransactionNotFoundException {

        Node curr = this.Head;
        while (curr.next != this.Last) {
            if (curr.next.data.getIdentifier().equals(id)) {
                Node Deleted = curr.next;
                Deleted.next.prev = curr;
                curr.next = Deleted.next;

                Deleted.next = null;
                Deleted.prev = null;
                Deleted.data = null;
                this.Size--;
                return ;
            }
            curr = curr.next;
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[]   toArray() {
        Transaction[]   array = new Transaction[this.Size];
        Node            curr = this.Head.next;

        for (int i = 0 ; i < this.Size; i++) {
            array[i] = curr.data;
            curr = curr.next;
        }
        return array;
    }

    void    display() {
        for (Node curr = this.Head.next; curr != this.Last; curr = curr.next)
            curr.data.display();
        System.out.println();
    }

}

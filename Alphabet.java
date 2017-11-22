import java.util.concurrent.locks.ReentrantLock;

public class Alphabet{
    public static void main(String[] args){
        char[] letter1 = {'A', 'E', 'I', 'M', 'Q', 'U', 'Y'};
        char[] letter2 = {'B', 'F', 'J', 'N', 'R', 'V', 'Z'};
        char[] letter3 = {'C', 'G', 'K', 'O', 'S', 'W'};
        char[] letter4 = {'D', 'H', 'L', 'P', 'T', 'X'};
        Thread thread1 = new Thread(new AlphabetOrder(letter1));
        Thread thread2 = new Thread(new AlphabetOrder(letter2));
        Thread thread3 = new Thread(new AlphabetOrder(letter3));
        Thread thread4 = new Thread(new AlphabetOrder(letter4));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

class AlphabetOrder implements Runnable{
    static char ch = 'A';
    ReentrantLock lock = new ReentrantLock();
    char[] alphabet;
    AlphabetOrder(){

    }
    AlphabetOrder(char[] letter){
        alphabet = new char[letter.length];
        for(int i = 0; i<letter.length; i++) {
            alphabet[i] = letter[i];
        }
    }
    public void run() {
        try {
            for (int i = 0; i < alphabet.length; i++)
                    printAlphabet(alphabet[i]);
            }
        catch(Exception e){
                System.out.println("Exception is caught.");
            }
        }
    public void printAlphabet(char c){
        while(c != ch) {
            try {
                wait();
            } catch (Exception e) {

            }
        }
        try{
            lock.lock();
            System.out.println(c);
            ch++;
        notifyAll();
        }
        catch (Exception e){

        }
        finally {
            lock.unlock();
        }
    }
}



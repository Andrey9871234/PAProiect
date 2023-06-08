/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author 37362
 * @param <T1>
 * @param <T2>
 */
public class Tuple<T1, T2> {
    private T1 item1;
    private T2 item2;
    
    public Tuple(T1 item1, T2 item2) {
        this.item1 = item1;
        this.item2 = item2;
    }
    
    public T1 getFirst() {
        return item1;
    }
    
    public T2 getSecond() {
        return item2;
    }

    
}

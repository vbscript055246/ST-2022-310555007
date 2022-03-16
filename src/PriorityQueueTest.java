import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Stream;

public class PriorityQueueTest {

    static Stream<Arguments> PQ_Tester(){
        List arr = new ArrayList();

        for(int i=0;i<5;i++){
            int[] temp_A = new int[5];
            int[] temp_B = new int[5];
            for(int j=0;j<temp_A.length;j++) {
                temp_A[j] = new Random().nextInt(99)-50;
                temp_B[j] = temp_A[j];
            }
            Arrays.sort(temp_B);
            arr.add(Arguments.of(temp_A, temp_B));
        }
        return arr.stream();
    }

    @ParameterizedTest(name="#{index} - Test with Argument={0},{1}")
    @MethodSource("PQ_Tester")
    public void PriorityQueue_RunTest(int[] input_arr, int[] answer_arr){
        PriorityQueue<Integer> que = new PriorityQueue<Integer>();
        int index = 0;
        int[] result = new int [input_arr.length];
        for (int i : input_arr) que.add(i);
        for (int i=0;i<input_arr.length;i++) result[i] = que.poll();
        assertArrayEquals(answer_arr, result);
    }

    @Test
    public void testException(){
        Exception e = assertThrows(NullPointerException.class, ()->{
            PriorityQueue<Integer> que = new PriorityQueue<Integer>();
            que.add(null);
        });

        Exception e1 = assertThrows(ClassCastException.class, ()->{
            PriorityQueue<PriorityQueue> que = new PriorityQueue<PriorityQueue>();
            que.add(que);
        });

        Exception e2 = assertThrows(NoSuchElementException.class, ()->{
            PriorityQueue<Integer> que = new PriorityQueue<Integer>();
            que.remove();
        });

        System.out.println(e);
        System.out.println(e1);
        System.out.println(e2);
//        String expectedMsg = "java.";
//        String actualMsg = e.getMessage();
//        assertEquals(actualMsg, expectedMsg);
    }

}

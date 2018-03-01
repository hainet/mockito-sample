import org.junit.Test;
import org.mockito.exceptions.misusing.InvalidUseOfMatchersException;

import static org.mockito.Mockito.*;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class MathServiceTest {

    @Test
    // モックインスタンスの対象メソッドが適切な引数で呼ばれていることを確認する。
    public void verifyTest() {
        // Setup
        final MathService mathService = mock(MathService.class);

        // Exercise
        // sumとsubtractがそれぞれ引数1で呼ばれていることを確認したい。
        mathService.sum(1);
        mathService.subtract(1);

        // Verify
        verify(mathService).sum(1);
        verify(mathService).subtract(1);
    }

    @Test
    // モックインスタンスの対象メソッドが適切な引数で適切な回数呼ばれていることを確認する。
    public void verifyTimesTest() {
        // Setup
        final MathService mathService = mock(MathService.class);

        // Exercise
        // sumが引数1で1回、引数2で2回呼ばれていることを確認したい。
        mathService.sum(1);
        mathService.sum(2);
        mathService.sum(2);

        // Verify
        verify(mathService, times(1)).sum(1);
        verify(mathService, times(2)).sum(2);
        verify(mathService, never()).sum(0);

        // 最低1回呼ばれていること
        verify(mathService, atLeastOnce()).sum(1);
        // 最低2回呼ばれていること
        verify(mathService, atLeast(2)).sum(2);
        // 最大3回までしか呼ばれないこと
        verify(mathService, atMost(3)).sum(2);
    }

    @Test
    // 引数に関わらず適切な回数呼ばれていることを確認する。
    public void verifyAnyIntTest() {
        // Setup
        final MathService mathService = mock(MathService.class);

        // Exercise
        // sumが引数1から100まで、それぞれ1回ずつ合計100回呼ばれていることを確認したい。
        for (int i=1; i<=100; i++) {
            mathService.sum(i);
        }

        // Verify
        verify(mathService, times(100)).sum(anyInt());
    }

    @Test
    // 複数の引数を持つメソッドにMatcherを適用する。
    public void correctArgumentMatcherTest() {
        // Setup
        final PlusComponent plusComponent = mock(PlusComponent.class);

        // Exercise
        plusComponent.run(1, 2);

        // Verify
        // 複数のMatcherを利用できることを確認したい。
        verify(plusComponent).run(anyInt(), anyInt());
    }

    @Test(expected = InvalidUseOfMatchersException.class)
    // 複数の引数を持つメソッドにMatcherと値を同時に適用する。
    public void incorrectArgumentMatcherTest() {
        // Setup
        final PlusComponent plusComponent = mock(PlusComponent.class);

        // Exercise
        plusComponent.run(1, 2);

        // Verify
        // Matcherと値が併用できないことを確認したい。
        verify(plusComponent).run(anyInt(), 2);
    }

    @Test
    // モックインスタンスの対象メソッドで任意の返り値を設定する。
    public void thenReturnTest() {
        // Setup
        final MathService mathService = mock(MathService.class);
        when(mathService.sum(1)).thenReturn(1);

        // Exercise & Verify
        // sumに設定した値が返却されることを確認したい。
        assertThat(mathService.sum(1), is(1));
        // sumに設定していない値は0で返却されることを確認したい。
        assertThat(mathService.sum(999), is(0));
    }

    @Test(expected = RuntimeException.class)
    // モックインスタンスの対象メソッドで任意の例外の発生を設定する。
    public void thenThrowTest() {
        // Setup
        final MathService mathService = mock(MathService.class);
        when(mathService.result()).thenThrow(new RuntimeException());

        // Exercise
        // resultに設定した例外が発生することを確認したい。
        mathService.result();
    }

    @Test
    public void whenAnyIntTest() {
        // Setup
        final MathService mathService = mock(MathService.class);
        when(mathService.sum(anyInt())).thenReturn(999);

        // Exercise & Verify
        // sumの引数に関わらず、設定した値が返却されることを確認したい。
        assertThat(mathService.sum(1), is(999));
        assertThat(mathService.sum(2), is(999));
        assertThat(mathService.sum(3), is(999));
    }

//    @Test
//    public void
}

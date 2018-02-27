import org.junit.Test;

import static org.mockito.Mockito.*;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class MathServiceTest {

    @Test
    // モックインスタンスの対象メソッドが適切な引数で呼ばれているか確認する。
    public void verifyTest() {
        final MathService mathService = mock(MathService.class);

        mathService.sum(1);
        mathService.subtract(1);

        verify(mathService).sum(1);
        verify(mathService).subtract(1);
    }

    @Test
    // モックインスタンスの対象メソッドが適切な引数で適切な回数呼ばれているか確認する。
    public void verifyTimesTest() {
        final MathService mathService = mock(MathService.class);

        mathService.sum(1);
        mathService.sum(2);
        mathService.sum(2);

        verify(mathService, times(1)).sum(1);
        verify(mathService, times(2)).sum(2);
    }

    @Test
    // モックインスタンスの対象メソッドで任意の返り値を設定する。
    public void thenReturnTest() {
        // Setup
        final MathService mathService = mock(MathService.class);
        when(mathService.sum(1)).thenReturn(1);

        // Exercise & Verify
        assertThat(mathService.sum(1), is(1));
        assertThat(mathService.sum(999), is(0));
    }

    @Test(expected = RuntimeException.class)
    // モックインスタンスの対象メソッドで任意の例外の発生を設定する。
    public void thenThrowTest() {
        // Setup
        final MathService mathService = mock(MathService.class);
        when(mathService.result()).thenThrow(new RuntimeException());

        // Exercise
        mathService.result();
    }
}

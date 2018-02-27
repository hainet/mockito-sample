import org.junit.Test;

import static org.mockito.Mockito.*;

public class MathServiceTest {

    @Test
    // モックインスタンスの対象メソッドが適切な引数で呼ばれているかのテスト
    public void verifyTest() {
        final MathService mathService = mock(MathService.class);

        mathService.sum(1);
        mathService.subtract(1);

        verify(mathService).sum(1);
        verify(mathService).subtract(1);
    }

    @Test
    // モックインスタンスの対象メソッドが適切な引数で適切な回数呼ばれているかのテスト
    public void verifyTimesTest() {
        final MathService mathService = mock(MathService.class);

        mathService.sum(1);
        mathService.sum(2);
        mathService.sum(2);

        verify(mathService, times(1)).sum(1);
        verify(mathService, times(2)).sum(2);
    }
}

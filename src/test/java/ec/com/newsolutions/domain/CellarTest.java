package ec.com.newsolutions.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ec.com.newsolutions.web.rest.TestUtil;

public class CellarTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cellar.class);
        Cellar cellar1 = new Cellar();
        cellar1.setId(1L);
        Cellar cellar2 = new Cellar();
        cellar2.setId(cellar1.getId());
        assertThat(cellar1).isEqualTo(cellar2);
        cellar2.setId(2L);
        assertThat(cellar1).isNotEqualTo(cellar2);
        cellar1.setId(null);
        assertThat(cellar1).isNotEqualTo(cellar2);
    }
}

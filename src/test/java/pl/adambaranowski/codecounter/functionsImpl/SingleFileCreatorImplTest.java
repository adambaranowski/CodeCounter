package pl.adambaranowski.codecounter.functionsImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.adambaranowski.codecounter.functions.SingleFileCreator;
import pl.adambaranowski.codecounter.model.SingleFile;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SingleFileCreatorImplTest {

    private SingleFileCreator singleFileCreator = new SingleFileCreatorImpl();

    @BeforeEach
    void setup(){
        singleFileCreator = new SingleFileCreatorImpl();
    }

    @Test
    void getExtensionShouldReturnCorrectExtension() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        File file = new File("testFile.txt");
        //when
        //reflection
        Method getExtension = singleFileCreator.getClass().getDeclaredMethod("getExtension", File.class);
        getExtension.setAccessible(true);
        String resultExtension = (String) getExtension.invoke(singleFileCreator,file);

        //then
        assertThat(resultExtension, equalTo("TXT"));
    }

    @Test
    void fileWithoutDeclaredExtensionShouldReturnUndefined() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        File fileWithoutExtension = new File("file");
        //when
        //reflection
        Method getExtension = SingleFileCreatorImpl.class.getDeclaredMethod("getExtension", File.class);
        getExtension.setAccessible(true);
        String resultExtension = (String) getExtension.invoke(singleFileCreator,fileWithoutExtension);

        //then
        assertThat(resultExtension, equalTo("UNDEFINED"));
    }

}
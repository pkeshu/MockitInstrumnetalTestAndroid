package com.keshar.mockitotestingexample.Data;

import android.os.Parcel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class UserTest {

    public static final String FIRSTNAME = "Keshar";
    public static final String LASTNAME = "paudel";
    public static final String COUNTRY = "nepal";
    public static final long BIRTH_DATE = 20151215;
    public static final String CITY = "Kathmandu";

    @Test
    public void testCorrectUserParcelableImplementation() {
        User user = new User(FIRSTNAME, LASTNAME, COUNTRY, BIRTH_DATE, CITY);
        Parcel parcel = Parcel.obtain();
        user.writeToParcel(parcel, user.describeContents());
        parcel.setDataPosition(0);
        User createdFromParcel = User.CREATOR.createFromParcel(parcel);

        assertNoFieldAeNull(createdFromParcel);

//        assertThat(createdFromParcel.getFirstname(), is(FIRSTNAME));
//        assertThat(createdFromParcel.getLastname(), is(LASTNAME));
//        assertThat(createdFromParcel.getCountry(), is(COUNTRY));
//        assertThat(createdFromParcel.getBirthDate(), is(BIRTH_DATE));
    }

    private void assertNoFieldAeNull(Object createdFromParcel) {
        List<Field> allField = extractAllFields(createdFromParcel);
        for (Field field : allField) {
            field.setAccessible(true);
            try {
                Object o = field.get(createdFromParcel);
                assertNotNull(field.getName() + " is null", o);
            } catch (IllegalAccessException e) {
                fail();
            }
        }
    }

    private List<Field> extractAllFields(Object createdFromParcel) {
        ArrayList<Field> result = new ArrayList<>();
        Field[] declaredFields = createdFromParcel.getClass().getDeclaredFields();
        Collections.addAll(result, declaredFields);
        Class superClass = createdFromParcel.getClass().getSuperclass();
        while (superClass != null && !superClass.toString().equals("class java.lang.Object")) {
            Collections.addAll(result, superClass.getDeclaredFields());
            superClass = superClass.getSuperclass();
        }
        return result;
    }
}

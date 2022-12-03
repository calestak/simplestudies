package com.techelevator.dao;

import com.techelevator.model.City;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JdbcCityDaoTests extends BaseDaoTests {

    private static final City CITY_1 = new City(1, "City 1", "AA", 11,111);
    private static final City CITY_2 = new City(2, "City 2", "BB", 22,222);
    private static final City CITY_4 = new City(4, "City 4", "AA", 44,444);

    private City testCity;

    private JdbcCityDao sut;

    @Before
    public void setup() {
        sut = new JdbcCityDao(dataSource);
        testCity = new City(0, "Test City", "CC", 99, 999);
    }

    @Test
    public void getCity_returns_correct_city_for_id() {
        Assert.fail();
    }

    @Test
    public void getCity_returns_null_when_id_not_found() {
        Assert.fail();
    }

    @Test
    public void getCitiesByState_returns_all_cities_for_state() {
        Assert.fail();
    }

    @Test
    public void getCitiesByState_returns_empty_list_for_abbreviation_not_in_db() {
        Assert.fail();
    }

    @Test
    public void createCity_returns_city_with_id_and_expected_values() {
        Assert.fail();
    }

    @Test
    public void created_city_has_expected_values_when_retrieved() {
        Assert.fail();
    }

    @Test
    public void updated_city_has_expected_values_when_retrieved() {
        Assert.fail();
    }

    @Test
    public void deleted_city_cant_be_retrieved() {
        Assert.fail();
    }

    private void assertCitiesMatch(City expected, City actual) {
        Assert.assertEquals(expected.getCityId(), actual.getCityId());
        Assert.assertEquals(expected.getCityName(), actual.getCityName());
        Assert.assertEquals(expected.getStateAbbreviation(), actual.getStateAbbreviation());
        Assert.assertEquals(expected.getPopulation(), actual.getPopulation());
        Assert.assertEquals(expected.getArea(), actual.getArea(), 0.1);
    }
}

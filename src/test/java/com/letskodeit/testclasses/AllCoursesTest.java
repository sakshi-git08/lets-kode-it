package com.letskodeit.testclasses;

import com.letskodeit.base.BaseTest;
import com.letskodeit.pageclasses.*;
import com.letskodeit.utilities.Constants;
import com.letskodeit.utilities.ExcelUtility;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AllCoursesTest extends BaseTest {

    @DataProvider(name = "verifySearchCourseData")
    public Object[][]getVerifySearchCourseData(){
        Object[][] testData = ExcelUtility.getTestData("verify_search_course");
        return testData;
    }


    @BeforeClass
    public void setUp()//to execute before running test method.
    {
        nav = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
        ExcelUtility.setExcelFile(Constants.EXCEL_FILE,"AllCoursesTest");
    }

    @Test(dataProvider = "verifySearchCourseData")
    public void verifySearchCourse(String courseName){
        nav.allCourses();
        nav.myCourses();
        search = new SearchBarPage(driver);
        result = search.course(courseName);
       boolean searchReult = result.verifySearchResult();
        Assert.assertTrue(searchReult);
    }

    @Test
    public void filterByCategory(){
        nav.allCourses();
         category = new CategoryFilterPage(driver);
         result = category.select("Software IT");
        //from the dropdown we can verify the number f courses it returns to verify whether correct result is displayed.
        int count=category.findCoursesCount("Software IT");
        boolean filterResult = result.verifyFilterCourseCount(count);
        Assert.assertTrue(filterResult);

    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}

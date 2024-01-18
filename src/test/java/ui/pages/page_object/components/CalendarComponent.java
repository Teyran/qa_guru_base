package ui.pages.page_object.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    private SelenideElement
            monthElement = $(".react-datepicker__month-select"),
            yearElement = $(".react-datepicker__year-select"),
            dayElement = $(".react-datepicker__month");

    public void setDate(String month, String day, String year) {
        monthElement.selectOption(month);
        yearElement.selectOption(year);
        dayElement.$(" .react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)").click();
    }

}

package mentimeter.project.mentimeter_case_study;

import java.util.List;
import java.util.TreeMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import mentimeter.project.utility.BaseClass;

public class CaseStudy extends BaseClass {

	@Test
	public void getBestInteractivePresentation() {

		Response questionResponse = getHTTPResponse(getMentimeterQuestionPath());

		String questionAsked = getStringJSONData(questionResponse, "question");

		System.out.println("Question asked :  " + questionAsked);

		Assert.assertEquals(questionAsked, getQuestionAskedOnAPI(), "Question doesn't match with the response");

		List<String> choicesForQuestionAsked = getListOfJSONData(questionResponse, "choices.label");

		System.out.println("Label choices for question asked are :  " + choicesForQuestionAsked);

		TreeMap<Integer, String> bestInteractivePresentation = new TreeMap<>();

		for (String label : choicesForQuestionAsked) {

			Response resultResponse = getHTTPResponse(getMentimeterResultPath());

			// Find the score of label in the result API when there is a match with question
			// API label.
			String searchLabel = "results.find {it.label == '" + label + "'}.score";

			List<String> result = getListOfJSONData(resultResponse, searchLabel);
			int score = Integer.parseInt(result.toString().replace("[", "").replace("]", ""));
			bestInteractivePresentation.put(score, label);

		}

		System.out.println("Result for best Interactive Presentation : " +bestInteractivePresentation);
	}

}
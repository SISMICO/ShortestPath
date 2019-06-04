package br.com.bexs.shortestpath;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ServiceTest {
	
	@Test
	public void testShortestPath() {
		String json = "[{\"origin\":\"GRU\",\"destination\":\"BRC\",\"distance\":10}]";
	    
		HttpUtil http = mock(HttpUtil.class);
		Service service = spy(Service.class);
		
		doReturn(json).when(http).get(any());
		doReturn(http).when(service).httpUtil();
		
		List<Route> expected = new ArrayList<>();
		expected.add(new Route("GRU", "BRC", 10));
		
	    List<Route> actual = service.shortestPath("GRU", "BRC");

	    assertEquals(expected.get(0).getOrigin(), actual.get(0).getOrigin());
	    assertEquals(expected.get(0).getDestination(), actual.get(0).getDestination());
	    assertEquals(expected.get(0).getDistance(), actual.get(0).getDistance());
	    assertEquals(expected.size(), actual.size());
	}

}

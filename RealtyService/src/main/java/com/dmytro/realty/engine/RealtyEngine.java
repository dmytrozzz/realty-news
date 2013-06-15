package com.dmytro.realty.engine;

import java.util.*;

import com.dmytro.realty.domain.Location;
import com.dmytro.realty.domain.Product;
import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.engine.parser.*;

public class RealtyEngine {
	private SendMan sendMan = new SendMan();
	private List<RealtyTeam> realtyTeams = new LinkedList<>();

	public RealtyEngine() {
		realtyTeams.addAll(RealtyTeam.createTeams());

	}

	/**
	 * Sends new offers to users
	 * 
	 * @param newRealtyOffers
	 *            list of new realty offers bigger then 0
	 * @param realtyUsers
	 *            list of users, subscribed for this criteria
	 */
	private void sendNews(List<RealtyOffer> newRealtyOffers,
			Collection<RealtyUser> realtyUsers) {
		sendMan.createMessage(newRealtyOffers);
		// TODO from DB already List or array of emails
		for (RealtyUser user : realtyUsers) {
			sendMan.addRecipient(user.getEmail());
		}
		sendMan.sendEmail();
	}

	/**
	 * Runs mechanism of searching new offers and sending it to users
	 * 
	 * @param criteria
	 *            {@link RealtyCriteria} for which users subscribed
	 * @param userCollection
	 *            collection of {@link RealtyUser}, subscribed for criteria
	 */
	public void searchAndSubscribe(RealtyCriteria criteria,
			Collection<RealtyUser> userCollection) throws RealtyUnparsebleException {

		List<RealtyOffer> resultOffers = new ArrayList<>();

		for (RealtyTeam team : realtyTeams)
			resultOffers.addAll(team.collectOffers(criteria));
		
		System.out.println("OK! Sending!");
		if (resultOffers.size() > 0)
			sendNews(resultOffers, userCollection);
	}

    public static RealtyCriteria criteria(Product.Type type,int fromP, int toP, int fromR, int toR, Product.Operation oper) {
        RealtyCriteria realtyCriteria = new RealtyCriteria();
        realtyCriteria.setProductType(type);
        realtyCriteria.getParameters().setFromPrice(fromP);
        realtyCriteria.getParameters().setToPrice(toP);

        realtyCriteria.getParameters().setFromRooms(fromR);
        realtyCriteria.getParameters().setToRooms(toR);

        realtyCriteria.setOperation(oper);
        return realtyCriteria;
    }

    public static List<Location> locations(Product.Location... locations){
        List<Location> locationList = new LinkedList<>();
        for(Product.Location l : locations)
            locationList.add(new Location(l));
        return locationList;
    }



	public static void main(String[] args) throws InterruptedException, RealtyUnparsebleException {

		RealtyCriteria criteria = criteria(Product.Type.APPARTMENT,200000,500000,1,3, Product.Operation.BUY);
        List<Location> location = null;


		RealtyUser user = new RealtyUser();
		user.setEmail("d.zonov@ukr.net");

        //RealtyUser user2 = new RealtyUser();
       // user2.setEmail("Vados77777@bigmir.net");

		RealtyEngine engine = new RealtyEngine();
		while (true) {
			System.out.println("Lets go!");
			engine.searchAndSubscribe(criteria,
                    Arrays.asList(user));
			Thread.sleep(20000);
		}

	}
}

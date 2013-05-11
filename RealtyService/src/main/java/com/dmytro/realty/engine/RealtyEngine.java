package com.dmytro.realty.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.domain.search.enums.OperationType;
import com.dmytro.realty.domain.search.enums.ProductType;
import com.dmytro.realty.engine.builder.RealtyRequestBuilder;
import com.dmytro.realty.engine.parser.SlandoRealtyParser;

public class RealtyEngine {
	private SendMan sendMan = new SendMan();
	private List<RealtyTeam> realtyTeams = new LinkedList<>();

	public RealtyEngine() {
		// Slando
		//realtyTeams.add(new RealtyTeam("slando", new RealtyRequestBuilder(),
		//		new SlandoRealtyParser()));
		
		// Aviso
				realtyTeams.add(new RealtyTeam("aviso", new RealtyRequestBuilder(),
						new SlandoRealtyParser()));
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
			Collection<RealtyUser> userCollection) {

		List<RealtyOffer> resultOffers = new ArrayList<>();

		for (RealtyTeam team : realtyTeams)
			resultOffers.addAll(team.collectOffers(criteria));

		if (resultOffers.size() > 0)
			sendNews(resultOffers, userCollection);
	}

	public static void main(String[] args) throws InterruptedException {
		RealtyCriteria realtyCriteria = new RealtyCriteria();
		realtyCriteria.setProductType(ProductType.APPARTMENT);
		realtyCriteria.getParameters().setFromPrice(2000);
		realtyCriteria.getParameters().setToPrice(4000);

		realtyCriteria.setOperation(OperationType.RENT);

		RealtyUser user = new RealtyUser();
		user.setEmail("d.zonov@ukr.net");

		RealtyEngine engine = new RealtyEngine();
		while (true) {
			System.out.println("Lets go!");
			engine.searchAndSubscribe(realtyCriteria,
					Collections.singletonList(user));
			Thread.sleep(20000);
		}

	}
}

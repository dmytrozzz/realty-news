<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- <form action="https://merchant.easypay.ua/client/order" method="post">
		<input type="hidden" name="merchant_id" value="2701a2c2cdfe483ea8ce354dd31991db" /> <input type="hidden" name="amount" value="50" /> <input
			type="hidden" name="desc" value="Оплата послуги" /> <input type="hidden" name="order_id" value="0" /> <input type="image"
			src="https://merchant.easypay.ua/content/images/easypay_pay2.png" value="Оплатить" />
	</form> -->

	<!-- <h:form id="payForm" prependId="false" onsubmit="document.#{p:component('payForm')}.action='https://merchant.easypay.ua/client/order';">
		<h:inputHidden id="merchant_id" value="2701a2c2cdfe483ea8ce354dd31991db" />
		<h:inputHidden id="amount" value="50" />
		<h:inputHidden id="desc" value="Оплата послуги" />
		<h:inputHidden id="order_id" value="0" />
		<p:commandButton id="saveButton" value="#{ui.PAY_FOR_SERVICE}" process="payForm" ajax="false" />
	</h:form> -->

	<!-- <p:commandButton id="saveButton" value="#{ui.PAY_FOR_SERVICE}" type="button"
		onclick="post('https://merchant.easypay.ua/client/order', {'merchant_id':'2701a2c2cdfe483ea8ce354dd31991db','amount':'5','desc':'paymoney','order_id':'0'})" /> -->
	<!-- 	<script src="https://checkout.stripe.com/v2/checkout.js"></script> -->

	<!-- <iframe src="https://wallapi.com/api/subscription/?key=098c740987a4d1de60242db7ba4e736e&amp;uid=123&amp;widget=p4_1" width="371" height="450"
		frameborder="0"/>


	<form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top">
		<input type="hidden" name="cmd" value="_xclick-subscriptions" /> <input type="hidden" name="business" value="dmytrozzz@i.ua" /> <input
			type="hidden" name="lc" value="UA" /> <input type="hidden" name="item_name" value="Subscription" /> <input type="hidden" name="item_number"
			value="123" /> <input type="hidden" name="no_note" value="1" /> <input type="hidden" name="src" value="1" /> <input type="hidden" name="a3"
			value="6.00" /> <input type="hidden" name="p3" value="1" /> <input type="hidden" name="t3" value="M" /> <input type="hidden"
			name="currency_code" value="USD" /> <input type="hidden" name="bn" value="PP-SubscriptionsBF:btn_subscribeCC_LG.gif:NonHostedGuest" /> <input
			type="image" src="https://www.paypalobjects.com/en_US/i/btn/btn_subscribeCC_LG.gif" border="0" name="submit"
			alt="PayPal - The safer, easier way to pay online!" /> <img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1"
			height="1" />
	</form>

	<script type="text/javascript" src="https://www.dwolla.com/scripts/button.min.js"></script>

	<form action="" method="post">
		<script src="https://checkout.stripe.com/v2/checkout.js" class="stripe-button" data-key="pk_test_ZNfqgWUywU3mbNwvtOo6DLbv" data-amount="2000"
			data-name="Demo Site" data-description="2 widgets ($20.00)" data-image="/128x128.png" panelLabel="Оплатити послугу">
			
		</script>
	</form>

	<a class="wepay-widget-button wepay-green" id="wepay_widget_anchor_51641eff0a95c" href="https://www.wepay.com/stores/648253/item/327069">Оплатити
		послугу</a>
	<script type="text/javascript">
		var WePay = WePay || {};
		WePay.load_widgets = WePay.load_widgets || function() {
		};
		WePay.widgets = WePay.widgets || [];
		WePay.widgets
				.push({
					object_id : 327069,
					widget_type : "store_item_buy_now",
					anchor_id : "wepay_widget_anchor_51641eff0a95c",
					widget_options : {
						store_id : 648253,
						show_item_price : false,
						show_item_images : true,
						button_text_sold_out : "Sold Out",
						show_item_custom_options : true,
						button_text : "\u041e\u043f\u043b\u0430\u0442\u0438\u0442\u0438 \u043f\u043e\u0441\u043b\u0443\u0433\u0443"
					}
				});
		if (!WePay.script) {
			WePay.script = document.createElement('script');
			WePay.script.type = 'text/javascript';
			WePay.script.async = true;
			WePay.script.src = 'https://static.wepay.com/min/js/widgets.v2.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(WePay.script, s);
		} else if (WePay.load_widgets) {
			WePay.load_widgets();
		}
	</script>

	<a class="dwolla_button" href="http://realtyservice.cloudfoundry.com" data-key="[YOUR KEY HERE]" data-tax="0" data-shipping="2.99" data-amount="9.95"
		data-desc="My goods are better than your goods" data-name="Goods" data-test="true">Buy My Goods</a> -->

	<!-- <button id="customButton" class="btn btn-succcess">Оплатити послугу</button>

	<script>
		$('#customButton')
				.click(
						function() {
							var token = function(res) {
								var $input = $(
										'<input type="hidden" name="stripeToken" />')
										.val(res.id);
								$('form').append($input).submit();
							};

							StripeCheckout.open({
								key : 'pk_test_czwzkTp2tactuLOEOqbMTRzG',
								address : true,
								amount : 5000,
								name : 'Joes Pistachios',
								description : 'A bag of Pistachios',
								panelLabel : 'Checkout',
								token : token
							});

							return false;
						});
	</script> -->

	<!-- <iframe frameborder="0" allowtransparency="true" scrolling="no"
		src="https://money.yandex.ru/embed/bank.xml?payer-type=entrep&amp;recipient-name=%D0%9E%D0%BB%D0%B5%D0%B3+%D0%A4.%D0%94.&amp;recipient-inn=500100732259&amp;recipient-kpp=&amp;recipient-bik=461278787&amp;recipient-account=12312341235145145134&amp;recipient-kbk=&amp;recipient-okato=&amp;writer=seller&amp;targets=%D0%9E%D0%BF%D0%BB%D0%B0%D1%82%D0%B0+%D0%BF%D0%BE%D1%81%D0%BB%D1%83%D0%B3&amp;default-sum=200&amp;nds-rate=1&amp;button-text=02"
		width="490" height="171"></iframe> -->
</body>
</html>


	public static void SummaryPageColumnSortsFunctionality() throws Exception {

		// Checking column sort Ascending and Descending using for loop

		// visible and have data!
		boolean HotListTab = driver.findElement(
				By.xpath("//a[@data-target='hotlist_hotlist']")).isDisplayed();
		boolean BillHoldTab = driver.findElement(
				By.xpath("//a[@data-target='holds_bill_hold']")).isDisplayed();
		boolean EntryHoldTab = driver.findElement(
				By.xpath("//a[@data-target='holds_entry_hold']")).isDisplayed();

		if (HotListTab == true && BillHoldTab == true && EntryHoldTab == true) {

			System.out.println("All Tabs are visible");
		} else {
			System.out
					.println("Data issue!! One of the tabs are not present. Abort Test!!");

			log.debug("All Tabs are visible");
		}

		for (int i = 0; i <= 2; i++) {

			boolean tmp;

			if (i == 0) {
				driver.findElement(
						By.xpath("//a[@data-target='hotlist_hotlist']"))
						.click();
				tmp = driver
						.findElement(
								By.xpath("//span[contains (text(), 'Shipments on Hotlist')]"))
						.isDisplayed();
				System.out.println("//Review Shipment for Hotlist:");
				Thread.sleep(3000);
			} else if (i == 1) {
				driver.findElement(
						By.xpath("//a[@data-target='holds_bill_hold']"))
						.click();
				tmp = driver
						.findElement(
								By.xpath("//span[contains (text(), 'Shipments on Bill Hold')]"))
						.isDisplayed();
				System.out.println("//Review Shipment for Bill Hold:");
				Thread.sleep(3000);
			} else {
				driver.findElement(
						By.xpath("//a[@data-target='holds_entry_hold']"))
						.click();
				tmp = driver
						.findElement(
								By.xpath("//span[contains (text(), 'Shipments on Entry Hold')]"))
						.isDisplayed();
				System.out.println("//Review Shipment for Entry Hold:");
				Thread.sleep(3000);
			}

			if (tmp != true) {

				System.out
						.println("Database Pull is taking longer than expected. Test aborted!");

				log.error("Database Pull is taking longer than expected. Test aborted!");

				System.exit(0);
			}

			// Check PRE-DEPARTURE VIEW Column //
			// names/////////////////////////////////////////////////
			ParentViewinc++;

			driver.findElement(By.id("selectedView")).click();

			boolean PreDepatureView = driver.findElement(
					By.xpath("//a[@data-value='preDepartureView']"))
					.isDisplayed();
			if (PreDepatureView == true) {
				driver.findElement(
						By.xpath("//a[@data-value='preDepartureView']"))
						.click();
				Thread.sleep(2000);
			} else {

				System.out
						.println("Pre-Departure View was not visible. Test aborted");

				log.error("Pre-Departure View was not visible. Test aborted");

				System.exit(0);
			}

			List<WebElement> PreDepatureColumnNames = driver.findElements(By
					.xpath("//*[@class='list-group-item headers']/div//li"));

			for (WebElement column : PreDepatureColumnNames) {
				// System.out.println("For the Pre-departure view, The Filter: "
				// + column.getText() + " should be visible");

				ChildViewInc++;
				Utilities.GetXMLAppDataColumnInformation();
				String str = tmpColumn;

				String tmp2 = str;

				switch (tmp2) {

				case "Enforcement Flags":
					// Do nothing
					// tmp = driver.findElement(By.linkText(str)).isEnabled();
					//
					// if (!tmp) {
					// log.info(str +
					// " Is not Clickable. This is correct behavior" );
					// }else{
					// log.error(str +
					// " Is  Clickable. This is Incorrect behavior");
					// }

					break;

				case "Informational Flags":

					// Do nothing

					break;

				case "Select All":

					driver.findElement(By.id("selectAll")).click();

					break;

				default:

					String xpathString = "//li/a[@data-sort='" + str + "']";
					driver.findElement(By.xpath(xpathString)).click();
					System.out
							.println("The Column: " + str + " Sort Ascending");
					Thread.sleep(1000);
					driver.findElement(By.xpath(xpathString)).click();
					System.out.println("The Column: " + str
							+ " Sort Descending");
					Thread.sleep(1000);

					break;
				}

			}
			System.out.println("Validation done for Pre-depature view");

			// Check MANIFEST // VIEW Column //
			// names////////////////////////////////////////////////////
			ParentViewinc++;
			ChildViewInc = 0;
			driver.findElement(By.id("selectedView")).click();

			boolean Manifest = driver.findElement(
					By.xpath("//a[@data-value='manifestView']")).isDisplayed();
			if (Manifest == true) {
				driver.findElement(By.xpath("//a[@data-value='manifestView']"))
						.click();
				Thread.sleep(3000);
			} else {

				System.out
						.println("Manifest View was not visible. Test aborted");

				log.error("Manifest View was not visible. Test aborted");

				System.exit(0);
			}

			List<WebElement> ManifestColumnNames = driver.findElements(By
					.xpath("//*[@class='list-group-item headers']/div//li"));

			for (WebElement column : ManifestColumnNames) {
				// System.out.println("For the Pre-departure view, The Filter: "
				// + column.getText() + " should be visible");

				ChildViewInc++;
				Utilities.GetXMLAppDataColumnInformation();
				String str = tmpColumn;

				String tmp3 = str;
				switch (tmp3) {

				case "Enforcement Flags":

					// Do nothing

					break;

				case "Informational Flags":

					// Do nothing

					break;

				case "Select All":

					driver.findElement(By.id("selectAll")).click();

					break;

				default:

					String xpathString = "//li/a[@data-sort='" + str + "']";
					driver.findElement(By.xpath(xpathString)).click();
					System.out.println("The Column: '" + str
							+ "' Sort Ascending");
					Thread.sleep(1000);
					driver.findElement(By.xpath(xpathString)).click();
					System.out.println("The Column: '" + str
							+ "' Sort Descending");
					Thread.sleep(1000);

					break;
				}

			}
			System.out.println("Validation done for Manifest view");

			// Check ENTRY VIEW // Column //
			// names////////////////////////////////////////////////////////
			ParentViewinc++;
			ChildViewInc = 0;
			driver.findElement(By.id("selectedView")).click();

			boolean Entry = driver.findElement(
					By.xpath("//a[@data-value='shipmentSummaryView']"))
					.isDisplayed();
			if (Entry == true) {
				driver.findElement(
						By.xpath("//a[@data-value='shipmentSummaryView']"))
						.click();
				Thread.sleep(1000);
			} else {

				System.out.println("ENTRY View was not visible. Test aborted");

				log.error("ENTRY View was not visible. Test aborted");

				System.exit(0);
			}

			List<WebElement> ENTRYColumnNames = driver.findElements(By
					.xpath("//*[@class='list-group-item headers']/div//li"));

			for (WebElement column : ENTRYColumnNames) {
				// System.out.println("For the Pre-departure view, The Filter: "
				// + column.getText() + " should be visible");

				ChildViewInc++;
				Utilities.GetXMLAppDataColumnInformation();
				String str = tmpColumn;

				String tmp4 = str;
				switch (tmp4) {

				case "Enforcement Flags":

					// Do nothing

					break;

				case "Informational Flags":

					// Do nothing

					break;

				case "Select All":

					driver.findElement(By.id("selectAll")).click();

					break;

				default:

					String xpathString = "//li/a[@data-sort='" + str + "']";
					driver.findElement(By.xpath(xpathString)).click();
					System.out
							.println("The Column: " + str + " Sort Ascending");
					Thread.sleep(2000);
					driver.findElement(By.xpath(xpathString)).click();
					System.out.println("The Column: " + str
							+ " Sort Descending");
					Thread.sleep(2000);

					break;
				}

			}

			System.out.println("Validation done for ENTRY view");

			// Check ISF VIEW // Column //
			// names///////////////////////////////////////////////////////////
			ParentViewinc++;
			ChildViewInc = 0;
			driver.findElement(By.id("selectedView")).click();

			boolean ISF = driver.findElement(
					By.xpath("//a[@data-value='summaryIsfView']"))
					.isDisplayed();
			if (ISF == true) {
				driver.findElement(
						By.xpath("//a[@data-value='summaryIsfView']")).click();
				Thread.sleep(2000);
			} else {

				System.out.println("ISF View was not visible. Test aborted");

				log.error("ISF View was not visible. Test aborted");

				System.exit(0);
			}

			List<WebElement> ISFColumnNames = driver.findElements(By
					.xpath("//*[@class='list-group-item headers']/div//li"));

			for (WebElement column : ISFColumnNames) {
				// System.out.println("For the Pre-departure view, The Filter: "
				// + column.getText() + " should be visible");

				ChildViewInc++;
				Utilities.GetXMLAppDataColumnInformation();
				String str = tmpColumn;

				String tmp5 = str;
				switch (tmp5) {

				case "Enforcement Flags":
					// Do nothing
					break;

				case "Informational Flags":
					// Do nothing
					break;

				case "Select All":
					driver.findElement(By.id("selectAll")).click();

					break;

				default:

					String xpathString = "//li/a[@data-sort='" + str + "']";

					driver.findElement(By.xpath(xpathString)).click();
					System.out
							.println("The Column: " + str + " Sort Ascending");
					Thread.sleep(2000);
					driver.findElement(By.xpath(xpathString)).click();
					System.out.println("The Column: " + str
							+ " Sort Descending");
					Thread.sleep(2000);
					break;
				}

			}

			System.out.println("ISF done for ENTRY view");

			log.info("ISF done for ENTRY view");

			ParentViewinc = 0;
			ChildViewInc = 0;

		}

	}

	

	public static void ToolTipFunctionality() throws Exception {

		// visible and have data!
		boolean HotListTab = driver.findElement(
				By.xpath("//a[@data-target='hotlist_hotlist']")).isDisplayed();
		boolean BillHoldTab = driver.findElement(
				By.xpath("//a[@data-target='holds_bill_hold']")).isDisplayed();
		boolean EntryHoldTab = driver.findElement(
				By.xpath("//a[@data-target='holds_entry_hold']")).isDisplayed();

		if (HotListTab == true & BillHoldTab == true & EntryHoldTab == true) {
			System.out.println("All Tabs are visible");
		} else {
			System.out
					.println("Data issue !! one of Tabs are not present. Abort Test!!");
			log.debug("All Tabs are visible");
		}

		for (int i = 0; i <= 2; i++) {
			boolean tmp;

			if (i == 0) {
				driver.findElement(
						By.xpath("//a[@data-target='hotlist_hotlist']"))
						.click();
				// tmp =
				// driver.findElement(By.xpath("//span[contains (text(), 'Shipments on Hotlist')]")).isDisplayed();
				// System.out.println("//Review Shipment for Hotlist:");
				tmp = driver
						.findElement(
								By.xpath("//span[contains (text(), 'Shipments on Hotlist')]"))
						.isDisplayed();
				System.out.println("Hotlist Summary Dashboard Page");
				Thread.sleep(3000);
			} else if (i == 1) {
				driver.findElement(
						By.xpath("//a[@data-target='holds_bill_hold']"))
						.click();
				tmp = driver
						.findElement(
								By.xpath("//span[contains(text), 'Shipment on Bill Hold']"))
						.isDisplayed();
				System.out.println("--Bill Hold Summary Dashboard Page");
				Thread.sleep(3000);
			} else {
				driver.findElement(
						By.xpath("//a[@data-target='holds_entry_hold']"))
						.click();
				tmp = driver
						.findElement(
								By.xpath("//span[contains (text(), 'Shipments on Entry Hold')]"))
						.isDisplayed();
				System.out.println("--Entry Hold Summary Dashboard Page");
				Thread.sleep(3000);
			}
			if (tmp != true) {
				System.out
						.println("Database pull is taking longer than expected. Test Aborted!");
				log.error("Database pull is taking longer than expected. Test Aborted!");
				System.exit(0);
			}
			driver.findElement(
					By.xpath("//a[@data-template-data-location='parties.bill']"))
					.click();

			Utilities.SwitchWindowTab(false, 0);

			String tmpTT = driver.findElement(
					By.xpath("//h3[@class='popover-title']")).getText();
			System.out.println("ToolTips Window PopUp: " + tmpTT);

		}
	}

	
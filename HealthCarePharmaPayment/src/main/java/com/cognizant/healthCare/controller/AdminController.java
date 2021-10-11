package com.cognizant.healthCare.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.healthCare.dao.AdminDetailsDaoSql;
import com.cognizant.healthCare.dao.CartDetalsDaoSql;
import com.cognizant.healthCare.dao.CustomerDetailsDaoSql;
import com.cognizant.healthCare.dao.MedicineDetailsDaoSql;
import com.cognizant.healthCare.dao.NotificationDaoSql;
import com.cognizant.healthCare.dao.OrderDetailsDaoSql;
import com.cognizant.healthCare.dao.OrderedMedicineDaoSql;
import com.cognizant.healthCare.dao.helpDetailsDaoSql;
import com.cognizant.healthCare.model.AdminDetails;
import com.cognizant.healthCare.model.MedicineDetails;
import com.cognizant.healthCare.model.helpDetails;
import com.cognizant.healthCare.model.orderDetails;
import com.cognizant.healthCare.service.PharmaPaymentService;

@Controller
public class AdminController {
	@Autowired
	PharmaPaymentService paymentService;
	@Autowired
	AdminDetailsDaoSql adminDao;
	@Autowired
	CustomerDetailsDaoSql customerDao;
	@Autowired
	MedicineDetailsDaoSql medicineDao;
	@Autowired
	OrderDetailsDaoSql orderDao;
	@Autowired
	OrderedMedicineDaoSql orderedMedicineDao;
	@Autowired
	CartDetalsDaoSql cartDao;
	@Autowired
	helpDetailsDaoSql helpDao;
	@Autowired
	NotificationDaoSql notificationDao;
	

	// First control reaches here and displays homePage
	@GetMapping("/home")
	public String homePage(ModelMap model) {
		return "homePage";
	}

	// If a user chooses admin, adminRegistrationPage is displayed.
	@GetMapping("/admin")
	public String adminRegistration(ModelMap model) {
		model.addAttribute("admin", new AdminDetails());
		return "adminRegistrationPage";
	}

	/*
	 * when admin enter all details and click register, the data is stored in
	 * database and return to same page with success message.
	 */
	@GetMapping("/adminRegistration")
	public String registerAdminDetails(ModelMap model, @ModelAttribute("admin") AdminDetails adminDetails,
			BindingResult result) throws ClassNotFoundException, SQLException, IOException, ParseException {
		adminDao.updateAdminDetails(adminDetails);
		model.addAttribute("message", "Your details are submitted successfully! Please login to continue.");
		return "adminRegistrationPage";
	}

	// When admin clicks login option, adminLoginPage is displayed.
	@GetMapping("/adminLoginPage")
	public String adminLoginPage(ModelMap model) {
		return "adminLoginPage";
	}

	/*
	 * When admin clicks login in adminLoginPage, that userName and Password is
	 * collected and checked with database. If it matches adminHomePage is
	 * displayed, else loginPage is displayed with error message. Meanwhile the
	 * available notification for admin is also checked if data matched.
	 */
	@PostMapping("/adminLogin")
	public String adminLogin(ModelMap model, @RequestParam String userName, @RequestParam String password)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		boolean message = paymentService.checkUserNameAndPasswordAdmin(userName, password);
		if (message) {
			List<String> list = notificationDao.getNotifications();
			if (list.isEmpty())
				;
			else {
				model.addAttribute("value", 100);
				model.addAttribute("notification", list);
			}
			model.addAttribute("username", userName);
			return "adminHomePage";
		}

		else {
			model.addAttribute("errorMessage", "Invalid User ID (or) Incorrect Password");
			return "adminLoginPage";
		}
	}

	// If admin clicks forgetPassword, adminForgetPassword page is displayed
	@GetMapping("/resetPasswordAdmin")
	public String adminPasswordReset(ModelMap model) {
		return "adminForgetPassword";
	}

	/*
	 * when admin clicks reset, all data is collected and checked with database. If
	 * it matches same page is returned with success message, else with error
	 * message
	 */
	@PostMapping("/updatePassword")
	public String adminResetPassword(ModelMap model, @RequestParam String userName,
			@RequestParam String securityQuestion, @RequestParam String securityAnswer, @RequestParam String password)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		boolean message = paymentService.checkSecurityQuestionAdmin(userName, password, securityQuestion,
				securityAnswer);
		if (message) {
			model.addAttribute("message", "Password Updated Successfully, Please login to continue!");
			return "adminForgetPassword";
		}

		else {
			model.addAttribute("errorMessage", "Invalid Security Question (or) Incorrect Security Answer");
			return "adminForgetPassword";
		}
	}

	/*
	 * Notification must be shown only once. So, that is truncated while reaching
	 * second time
	 */
	@GetMapping("/adminHomePage")
	public String adminhomePage(ModelMap model) throws ClassNotFoundException, SQLException, IOException {
		notificationDao.truncateNotification();
		return "adminHomePage";
	}

	/*
	 * Admin, when clicks medicineInformation, it fetches all MedicineDetails
	 * objects from database and passed to MedicineInformation.jsp
	 */
	@GetMapping("/medicineInformation")
	public String addMedicine(ModelMap model) throws ClassNotFoundException, SQLException, IOException, ParseException {
		List<MedicineDetails> medicine = medicineDao.getMedicineDetails();
		model.addAttribute("medicineDetails", medicine);
		return "MedicineInformation";
	}

	/*
	 * If admin like to edit particular medicineDetails, that id is received and
	 * that particular MedicineDetails object is fetched from database and passed to
	 * editMedicineInformation.jsp
	 */
	@GetMapping("/editMedicineInformation")
	public String getMenuItem(ModelMap model, @RequestParam int medicineId)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		model.addAttribute("medicine", medicineDao.getMedicineDetailsById(medicineId));
		model.addAttribute("medicineDetails", new MedicineDetails());
		return "editMedicineInformation";
	}

	/*
	 * If admin like to save the changes, that data is collected and stored in
	 * database and the same page is returned with success message.
	 */
	@GetMapping("/saveEditedMedicineInformation")
	public String editMenuItem(ModelMap model, @ModelAttribute("medicineDetails") MedicineDetails medicineDetails)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		medicineDao.updateMedicineDetails(medicineDetails);
		model.addAttribute("message", "Saved Successfully!!!!");
		return "editMedicineInformation";
	}

	/*
	 * If admin likes to remove any medicine, that id is collected and that medicine
	 * is removed from database and returned with success message.
	 */
	@GetMapping("/removeMedicineInformation")
	public String removeMedicineInformation(ModelMap model, @RequestParam int medicineId)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		medicineDao.deleteMedicineInformation(medicineId);
		model.addAttribute("message", "Medicine Removed Successfully!");
		List<MedicineDetails> medicine = medicineDao.getMedicineDetails();
		model.addAttribute("medicineDetails", medicine);
		return "MedicineInformation";
	}

	// If admin wants to add new medicine, addNewMedicinePage is displayed.
	@GetMapping("/addNewMedicine")
	public String addNewMedicine(ModelMap model)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		model.addAttribute("medicine", new MedicineDetails());
		return "addNewMedicinePage";
	}

	/*
	 * when admin tries to save new medicine details, that data is collected and
	 * stored in database.
	 */
	@GetMapping("/submitAddedMedicine")
	public String saveNewMedicine(ModelMap model, @ModelAttribute("medicine") MedicineDetails medicineDetails)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		medicineDao.addMedicineDetails(medicineDetails);
		model.addAttribute("message", "Added Successfully!");
		return "addNewMedicinePage";
	}

	/*
	 * when admin clicks updateStock, all medicineDetails are fetched from database
	 * and passed to jsp
	 */
	@GetMapping("/updateStock")
	public String updateStock(ModelMap model) throws ClassNotFoundException, SQLException, IOException, ParseException {
		List<MedicineDetails> medicine = medicineDao.getMedicineDetails();
		model.addAttribute("medicineDetails", medicine);
		return "updateStock";
	}

	/*
	 * If admin wants to edit stock of particular medicine, using that medicine name
	 * its original stock value in database is fetched and passed.
	 */
	@GetMapping("/editStock")
	public String editStockPage(ModelMap model, @RequestParam String medicineName)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		int stock = medicineDao.getStockDetails(medicineName);
		model.addAttribute("medicineName", medicineName);
		model.addAttribute("stock", stock);
		return "stockEditPage";
	}

	/*
	 * Once admin clicks save that medicine name and edited stock value is received
	 * and updated in database
	 */
	@PostMapping("/saveEditedStock")
	public String editMedicineStock(ModelMap model, @RequestParam String medicineName, @RequestParam int stock)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		medicineDao.updateStockDetails(medicineName, stock);
		List<MedicineDetails> medicine = medicineDao.getMedicineDetails();
		model.addAttribute("medicineDetails", medicine);
		model.addAttribute("message", "Stock Updated successfully!!");
		return "updateStock";
	}

	// If admin likes to view all stocks, that details are fetched from database
	@GetMapping("/viewStock")
	public String viewStock(ModelMap model) throws ClassNotFoundException, SQLException, IOException, ParseException {
		List<MedicineDetails> medicine = medicineDao.getMedicineDetails();
		model.addAttribute("medicineDetails", medicine);
		return "viewStock";
	}

	/*
	 * If admin likes to view stock of particular type of medicine (i.e ointment,
	 * syrup, etc.) the preferred type is received and only that medicineDetails are
	 * fetched from database
	 */
	@GetMapping("/searchByType")
	public String viewStockbasedOnType(ModelMap model, @RequestParam String type)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		List<MedicineDetails> medicine = medicineDao.getMedicineDetails(type);
		model.addAttribute("medicineDetails", medicine);
		return "viewStock";
	}

	/*
	 * If admin likes to view all the orders placed, those details are fetched from
	 * database.
	 */
	@GetMapping("/viewOrder")
	public String viewOrders(ModelMap model) throws ClassNotFoundException, SQLException, IOException, ParseException {
		List<orderDetails> orders = orderDao.getOrderDetailsAdmin();
		List<HashMap<String, Integer>> medicineList = orderDao.getorderedMedicines(orders);
		model.addAttribute("order", orders);
		model.addAttribute("medicineList", medicineList);
		return "viewOrder";
	}

	@GetMapping("/updateStatus")
	public String updateStatus(ModelMap model, @RequestParam int orderId)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		model.addAttribute("orderId", orderId);
		return "updateStatusPage";
	}

	@PostMapping("/saveUpdatedStatus")
	public String saveUpdatedStatus(ModelMap model, @RequestParam int orderId, @RequestParam String status)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		orderDao.updateStatus(orderId, status);
		model.addAttribute("message", "Status Updated successfully!!");
		return "updateStatusPage";
	}

	@GetMapping("/generateReport")
	public String generateReport(ModelMap model)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		List<MedicineDetails> medicine = medicineDao.getMedicineDetails();
		model.addAttribute("medicineDetails", medicine);
		return "generateReport";
	}

	@GetMapping("/brandReport")
	public String brandWise(ModelMap model) throws ClassNotFoundException, SQLException, IOException, ParseException {
		List<MedicineDetails> medicine = medicineDao.getMedicineDetails();
		model.addAttribute("medicineDetails", medicine);
		return "brandReport";
	}

	@GetMapping("/dateReport")
	public String dateWise(ModelMap model) throws ClassNotFoundException, SQLException, IOException, ParseException {
		List<MedicineDetails> medicine = medicineDao.getMedicineDetails();
		model.addAttribute("medicineDetails", medicine);
		return "dateReport";
	}

	@GetMapping("/diseaseReport")
	public String diseaseWise(ModelMap model) throws ClassNotFoundException, SQLException, IOException, ParseException {
		List<MedicineDetails> medicine = medicineDao.getMedicineDetails();
		model.addAttribute("medicineDetails", medicine);
		return "diseaseReport";
	}

	@GetMapping("/helpRequest")
	public String viewHelp(ModelMap model) throws ClassNotFoundException, SQLException, IOException, ParseException {
		List<helpDetails> help = helpDao.getHelpDetailsAdmin();
		model.addAttribute("help", help);
		return "viewHelp";
	}

	@GetMapping("/replyHelp")
	public String replyHelp(ModelMap model, @RequestParam int ticketId)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		model.addAttribute("id", ticketId);
		return "replyHelp";
	}

	@PostMapping("/saveReply")
	public String saveReply(ModelMap model, @RequestParam int ticketId, @RequestParam String reply)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		helpDao.updateHelp(ticketId, reply);
		model.addAttribute("message", "Status Updated successfully!!");
		return "replyHelp";
	}

}

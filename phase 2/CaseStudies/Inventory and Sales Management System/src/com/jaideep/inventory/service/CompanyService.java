
package com.jaideep.inventory.service;

import com.jaideep.inventory.model.Company;

import java.util.HashMap;
import java.util.Map;

public class CompanyService {

    private final Map<Integer, Company> companies;

    public CompanyService() {
        companies = new HashMap<>();
    }

    public void addCompany(Company company) {

        if (company == null)
            throw new IllegalArgumentException("Company cannot be null");

        int id = company.getCompanyId();

        if (id <= 0)
            throw new IllegalArgumentException("Invalid Company ID");

        if (companies.containsKey(id))
            throw new IllegalArgumentException(
                    "Company ID already exists: " + id
            );

        companies.put(id, company);

        System.out.println("Company added successfully!");
    }

    public void updateCompany(Company company) {

        if (company == null)
            throw new IllegalArgumentException("Company cannot be null");

        updateCompany(
                company.getCompanyId(),
                company.getCompanyName(),
                company.getContactDetails(),
                company.getLocation()
        );
    }

    public void updateCompany(int companyId,
                              String companyName,
                              String contactDetails,
                              String location) {

        Company company = findCompany(companyId);

        company.setCompanyName(companyName);
        company.setContactDetails(contactDetails);
        company.setLocation(location);

        System.out.println("Company updated successfully!");
    }

    public boolean companyExists(int companyId) {
        return companies.containsKey(companyId);
    }

    public Company getCompany(int companyId) {
        return companies.get(companyId);
    }

    public Company findCompany(int companyId) {

        Company company = companies.get(companyId);

        if (company == null)
            throw new IllegalArgumentException(
                    "Company not found: " + companyId
            );

        return company;
    }

    public void displayAllCompanies() {

        if (companies.isEmpty()) {
            System.out.println("No companies available.");
            return;
        }

        for (Company company : companies.values()) {
            System.out.println(company);
            System.out.println("----------------------");
        }
    }

    public void deleteCompany(int companyId) {

        if (!companies.containsKey(companyId))
            throw new IllegalArgumentException("Company not found");

        companies.remove(companyId);

        System.out.println("Company deleted successfully!");
    }

    // Expose company lookup to other services
    public Map<Integer, Company> getCompanies() {
        return Map.copyOf(companies);
    }

}

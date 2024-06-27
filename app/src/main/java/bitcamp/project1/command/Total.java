package bitcamp.project1.command;

import bitcamp.project1.util.restAmount;

public class Total {
    private Income income;
    private Expenditure expenditure;

    public Total(Income income, Expenditure expenditure) {
        this.income = income;
        this.expenditure = expenditure;
    }

    public void showTotal() {
        System.out.println("\n[총합]");
        int totalIncome = income.getTotal();
        int totalExpenditure = expenditure.getTotal();
        int balance = totalIncome - totalExpenditure;

        System.out.println("총 수입: " + restAmount.formatNumber(totalIncome) + "원");
        System.out.println("총 지출: " + restAmount.formatNumber(totalExpenditure) + "원");
        System.out.println("잔액: " + restAmount.formatNumber(balance) + "원");
    }
}

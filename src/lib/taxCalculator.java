package lib;

/**
 * 
 * Class TaxFunction merupakan kelas yang digunakan untuk menghitung jumlah
 * pajak penghasilan pegawai yang harus dibayarkan setahun.
 */
public class TaxFunction {
    /**
     * Method untuk menghitung jumlah pajak yang harus dibayarkan.
     * 
     * @param monthlySalary        gaji bulanan pegawai
     * @param otherMonthlyIncome   pemasukan bulanan lainnya selain gaji
     * @param numberOfMonthWorking jumlah bulan bekerja dalam setahun
     * @param deductible           jumlah pengurangan yang berlaku pada penghasilan
     *                             bruto pegawai
     * @param isMarried            status pernikahan pegawai (true jika sudah
     *                             menikah, false jika belum menikah)
     * @param numberOfChildren     jumlah anak yang dimiliki oleh pegawai
     * @return jumlah pajak yang harus dibayarkan dalam setahun
     */
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible,
            boolean isMarried, int numberOfChildren) {

        int tax = 0;

        if (numberOfMonthWorking > 12) {
            System.err.println("Lebih dari 12 bulan bekerja per tahun");
        }

        // batasi jumlah anak yang dimiliki hingga maksimal 3 anak
        if (numberOfChildren > 3) {
            numberOfChildren = 3;
        }

        // hitung jumlah pajak yang harus dibayarkan
        int taxableIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking - deductible;
        int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);
        tax = (int) Math.round(0.05 * (taxableIncome - nonTaxableIncome));

        // pastikan jumlah pajak tidak negatif
        if (tax < 0) {
            return 0;
        } else {
            return tax;
        }

    }

    /**
     * Method untuk menghitung jumlah penghasilan yang tidak kena pajak.
     * 
     * @param isMarried        status pernikahan pegawai (true jika sudah menikah,
     *                         false jika belum menikah)
     * @param numberOfChildren jumlah anak yang dimiliki oleh pegawai
     * @return jumlah penghasilan yang tidak kena pajak dalam setahun
     */
    private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {

        int nonTaxableIncome = 54000000; // penghasilan tidak kena pajak awal

        // jika sudah menikah, tambahkan jumlah penghasilan tidak kena pajak untuk
        // pasangan suami/istri
        if (isMarried) {
            nonTaxableIncome += 4500000;
        }

        // tambahkan jumlah penghasilan tidak kena pajak untuk setiap anak hingga anak
        // ketiga
        if (numberOfChildren >= 1) {
            nonTaxableIncome += 4500000;
        }
        if (numberOfChildren >= 2) {
            nonTaxableIncome += 4500000;
        }
        if (numberOfChildren >= 3) {
            nonTaxableIncome += 4500000;
        }

        return nonTaxableIncome;
    }
}
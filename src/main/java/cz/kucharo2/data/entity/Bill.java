package cz.kucharo2.data.entity;

import cz.kucharo2.data.enums.BillStatus;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Date;

/**
 * @Author Roman Kuchár <kucharrom@gmail.com>.
 */
@Entity
@Table(name = Bill.TABLE_NAME)
@Proxy(lazy = false)
public class Bill extends DtoEntity {

    public static final String TABLE_NAME = "Bill";
    public static final String BILL_ID = "bil_id";
    public static final String DATE = "date";
    public static final String STATUS = "status";
    public static final String TABLE_ID = "table_id";
    public static final String ACCOUNT_ID = "account_id";

    @Id
    @Column(name = BILL_ID)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = TABLE_ID)
    private RestaurantTable table;

    @Column(name = DATE)
    private Date date = new Date();

    @Column(name = STATUS)
    @Enumerated(EnumType.STRING)
    private BillStatus status;

    @ManyToOne
    @JoinColumn(name = ACCOUNT_ID)
    private Account account;

    @OneToMany(mappedBy = "bill", fetch = FetchType.EAGER)
    private Collection<BillItem> billItems;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RestaurantTable getTable() {
        return table;
    }

    public void setTable(RestaurantTable table) {
        this.table = table;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Collection<BillItem> getBillItems() {
        return billItems;
    }

    public void setBillItems(Collection<BillItem> billItems) {
        this.billItems = billItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bill bill = (Bill) o;

        if (id != null ? !id.equals(bill.id) : bill.id != null) return false;
        if (table != null ? !table.equals(bill.table) : bill.table != null) return false;
        if (date != null ? !date.equals(bill.date) : bill.date != null) return false;
        if (account != null ? !account.equals(bill.account) : bill.account != null) return false;
        return billItems != null ? billItems.equals(bill.billItems) : bill.billItems == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (table != null ? table.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (billItems != null ? billItems.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", table=" + table +
                ", date=" + date +
                ", account=" + account +
                ", billItems=" + billItems +
                '}';
    }
}

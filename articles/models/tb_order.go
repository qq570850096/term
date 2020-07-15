package models

import (
	"errors"
	"fmt"
	"reflect"
	"strings"
	"time"

	"github.com/astaxie/beego/orm"
)

type TbOrder struct {
	Id           int       `orm:"column(id);auto"`
	OrderNum     string    `orm:"column(order_num);size(32)" description:"订单流水号"`
	CurriculumId uint      `orm:"column(curriculum_id)" description:"课程ID"`
	UserId       uint      `orm:"column(user_id)" description:"用户ID"`
	Price        float64   `orm:"column(price);digits(10);decimals(2)" description:"购买价格"`
	CreateTime   time.Time `orm:"column(create_time);type(timestamp);auto_now_add" description:"购买时间"`
}

func (t *TbOrder) TableName() string {
	return "tb_order"
}

func init() {
	orm.RegisterModel(new(TbOrder))
}

// AddTbOrder insert a new TbOrder into database and returns
// last inserted Id on success.
func AddTbOrder(m *TbOrder) (id int64, err error) {
	o := orm.NewOrm()
	id, err = o.Insert(m)
	return
}

// GetTbOrderById retrieves TbOrder by Id. Returns error if
// Id doesn't exist
func GetTbOrderById(id int) (v *TbOrder, err error) {
	o := orm.NewOrm()
	v = &TbOrder{Id: id}
	if err = o.Read(v); err == nil {
		return v, nil
	}
	return nil, err
}

// GetAllTbOrder retrieves all TbOrder matches certain condition. Returns empty list if
// no records exist
func GetAllTbOrder(query map[string]string, fields []string, sortby []string, order []string,
	offset int64, limit int64) (ml []interface{}, err error) {
	o := orm.NewOrm()
	qs := o.QueryTable(new(TbOrder))
	// query k=v
	for k, v := range query {
		// rewrite dot-notation to Object__Attribute
		k = strings.Replace(k, ".", "__", -1)
		if strings.Contains(k, "isnull") {
			qs = qs.Filter(k, (v == "true" || v == "1"))
		} else {
			qs = qs.Filter(k, v)
		}
	}
	// order by:
	var sortFields []string
	if len(sortby) != 0 {
		if len(sortby) == len(order) {
			// 1) for each sort field, there is an associated order
			for i, v := range sortby {
				orderby := ""
				if order[i] == "desc" {
					orderby = "-" + v
				} else if order[i] == "asc" {
					orderby = v
				} else {
					return nil, errors.New("Error: Invalid order. Must be either [asc|desc]")
				}
				sortFields = append(sortFields, orderby)
			}
			qs = qs.OrderBy(sortFields...)
		} else if len(sortby) != len(order) && len(order) == 1 {
			// 2) there is exactly one order, all the sorted fields will be sorted by this order
			for _, v := range sortby {
				orderby := ""
				if order[0] == "desc" {
					orderby = "-" + v
				} else if order[0] == "asc" {
					orderby = v
				} else {
					return nil, errors.New("Error: Invalid order. Must be either [asc|desc]")
				}
				sortFields = append(sortFields, orderby)
			}
		} else if len(sortby) != len(order) && len(order) != 1 {
			return nil, errors.New("Error: 'sortby', 'order' sizes mismatch or 'order' size is not 1")
		}
	} else {
		if len(order) != 0 {
			return nil, errors.New("Error: unused 'order' fields")
		}
	}

	var l []TbOrder
	qs = qs.OrderBy(sortFields...)
	if _, err = qs.Limit(limit, offset).All(&l, fields...); err == nil {
		if len(fields) == 0 {
			for _, v := range l {
				ml = append(ml, v)
			}
		} else {
			// trim unused fields
			for _, v := range l {
				m := make(map[string]interface{})
				val := reflect.ValueOf(v)
				for _, fname := range fields {
					m[fname] = val.FieldByName(fname).Interface()
				}
				ml = append(ml, m)
			}
		}
		return ml, nil
	}
	return nil, err
}

// UpdateTbOrder updates TbOrder by Id and returns error if
// the record to be updated doesn't exist
func UpdateTbOrderById(m *TbOrder) (err error) {
	o := orm.NewOrm()
	v := TbOrder{Id: m.Id}
	// ascertain id exists in the database
	if err = o.Read(&v); err == nil {
		var num int64
		if num, err = o.Update(m); err == nil {
			fmt.Println("Number of records updated in database:", num)
		}
	}
	return
}

// DeleteTbOrder deletes TbOrder by Id and returns error if
// the record to be deleted doesn't exist
func DeleteTbOrder(id int) (err error) {
	o := orm.NewOrm()
	v := TbOrder{Id: id}
	// ascertain id exists in the database
	if err = o.Read(&v); err == nil {
		var num int64
		if num, err = o.Delete(&TbOrder{Id: id}); err == nil {
			fmt.Println("Number of records deleted in database:", num)
		}
	}
	return
}
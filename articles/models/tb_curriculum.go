package models

import (
	"bytes"
	"errors"
	"fmt"
	"reflect"
	"strings"
	"time"

	"github.com/astaxie/beego/orm"
)

type TbCurriculum struct {
	Id            int       `orm:"column(id);auto" json:"id"`
	Title         string    `orm:"column(title);size(200)" description:"课程标题" json:"title"` 
	Price         float64   `orm:"column(price);digits(10);decimals(2)" description:"售价" json:"price"`
	CurrentPrice  float64   `orm:"column(current_price);digits(10);decimals(2)" description:"当前售价" json:"current_price"`
	Banner        string    `orm:"column(banner);size(200)" description:"条幅地址" json:"banner"`
	LearningCycle string    `orm:"column(learning_cycle)" description:"学习周期" json:"learning_cycle"`
	TypeId        uint      `orm:"column(type_id)" description:"类型ID" json:"type_id"`
	Tag           string    `orm:"column(tag);size(70)" description:"标签" json:"tag"`
	Suggestion    string    `orm:"column(suggestion);size(255)" description:"推荐介绍" json:"suggestion"`
	Difficulty    string    `orm:"column(difficulty)" description:"难度等级" json:"difficulty"`
	State         string    `orm:"column(state)" description:"状态" json:"state"`
	Top           uint8     `orm:"column(top)" description:"是否置顶" json:"top"`
	VideoNum      uint      `orm:"column(video_num);null" description:"视频数量" json:"video_num"`
	DocumentNum   uint      `orm:"column(document_num);null" description:"文档数量" json:"document_num"`
	CreateTime    time.Time `orm:"column(create_time);type(timestamp);auto_now_add" description:"创建时间" json:"create_time"`
}

func (t *TbCurriculum) TableName() string {
	return "tb_curriculum"
}

func init() {
	orm.RegisterModel(new(TbCurriculum))
}

// AddTbCurriculum insert a new TbCurriculum into database and returns
// last inserted Id on success.
func AddTbCurriculum(m *TbCurriculum) (id int64, err error) {
	o := orm.NewOrm()
	id, err = o.Insert(m)
	return
}

// GetTbCurriculumById retrieves TbCurriculum by Id. Returns error if
// Id doesn't exist
func GetTbCurriculumById(id int) (v *TbCurriculum, err error) {
	o := orm.NewOrm()
	v = &TbCurriculum{Id: id}
	if err = o.Read(v); err == nil {
		return v, nil
	}
	return nil, err
}

// GetAllTbCurriculum retrieves all TbCurriculum matches certain condition. Returns empty list if
// no records exist
func GetAllTbCurriculum(query map[string]string, fields []string, sortby []string, order []string,
	offset int64, limit int64) (ml []interface{}, err error) {
	o := orm.NewOrm()
	qs := o.QueryTable(new(TbCurriculum))
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

	var l []TbCurriculum
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

// UpdateTbCurriculum updates TbCurriculum by Id and returns error if
// the record to be updated doesn't exist
func UpdateTbCurriculumById(m *TbCurriculum) (err error) {
	o := orm.NewOrm()
	v := TbCurriculum{Id: m.Id}
	// ascertain id exists in the database
	if err = o.Read(&v); err == nil {
		var num int64
		if num, err = o.Update(m); err == nil {
			fmt.Println("Number of records updated in database:", num)
		}
	}
	return
}

// DeleteTbCurriculum deletes TbCurriculum by Id and returns error if
// the record to be deleted doesn't exist
func DeleteTbCurriculum(id int) (err error) {
	o := orm.NewOrm()
	v := TbCurriculum{Id: id}
	// ascertain id exists in the database
	if err = o.Read(&v); err == nil {
		var num int64
		if num, err = o.Delete(&TbCurriculum{Id: id}); err == nil {
			fmt.Println("Number of records deleted in database:", num)
		}
	}
	return
}

func SearchTop()([]orm.Params, error){
	o:=orm.NewOrm()
	var res []orm.Params
	_,err := o.Raw(`select id,banner from tb_curriculum where state='上线'AND top=1`).Values(&res)
	return res,err
}

func SearchHot() (orm.Params, error) {
	o:=orm.NewOrm()
	var res []orm.Params
	_,err := o.Raw(`select
					c.id,
					c.title as name,
					c.suggestion,
					tp.icon,
					th.title,
					th.nick_name
					from tb_curriculum c
					join (select id from tb_curriculum_sales ORDER BY buyers DESC LIMIT 1)t
					on c.id=t.id
					join tb_curriculum_type tp on tp.id=c.type_id
					join tb_curriculum_teacher cth on c.id=cth.curriculum_id		
					join tb_teacher th on cth.teacher_id=th.id`).Values(&res)
	return res[0],err
}

func SearchRefined(diffi string)([]orm.Params, error) {
	o:=orm.NewOrm()
	var res []orm.Params
	_,err := o.Raw(`select
					c.id,
					c.title as name,
					c.learning_cycle,
					c.tag,
					tp.icon,
					th.title,
					th.nick_name
					from
					tb_curriculum c
					join tb_curriculum_type tp on tp.id = c.type_id
					join tb_curriculum_teacher cth on c.id=cth.curriculum_id
					join tb_teacher th on cth.teacher_id=th.id
					where c.difficulty=? and c.state='上线'
					order by c.id desc
					limit 10`,diffi).Values(&res)
	return res,err
}


func SearchByPage(search SearchForm)([]orm.Params, error) {
	o:=orm.NewOrm()
	var res []orm.Params

	str := `select
					c.id,
					c.title as name,
					c.learning_cycle,
					c.tag,
					tp.icon,
					th.title,
					th.nick_name
					from
					tb_curriculum c
					join tb_curriculum_type tp on tp.id = c.type_id
					join tb_curriculum_teacher cth on c.id=cth.curriculum_id
					join tb_teacher th on cth.teacher_id=th.id
					where c.state='上线'`
	var buffer bytes.Buffer
	buffer.WriteString(str)
	if search.Difficulty!="" {
		buffer.WriteString(" AND c.difficulty="+"'"+search.Difficulty +"'")
	}
	if search.Keyword != "" {
		buffer.WriteString(" AND c.title LIKE "+"'%"+search.Keyword +"%'")
	}
	buffer.WriteString(" order by c.id desc limit ?,?")
	_,err := o.Raw(buffer.String(),(search.Page-1)*search.Length,search.Page*search.Length).Values(&res)
	return res,err
}
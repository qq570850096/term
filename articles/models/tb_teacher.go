package models

import (
	"github.com/astaxie/beego/orm"
)

type TbTeacher struct {
	Id           int    `orm:"column(id);auto"`
	NickName     string `orm:"column(nick_name);size(200)" description:"昵称"`
	Photo        string `orm:"column(photo);size(200);null" description:"头像"`
	Introduction string `orm:"column(introduction);size(500)" description:"讲师介绍"`
	Title        string `orm:"column(title);null" description:"职称"`
	Curriculums  uint   `orm:"column(curriculums)" description:"课程数量"`
	Rating       string `orm:"column(rating);size(4)" description:"好评度"`
	Students     uint   `orm:"column(students)" description:"学生数量"`
}

func (t *TbTeacher) TableName() string {
	return "tb_teacher"
}

func init() {
	orm.RegisterModel(new(TbTeacher))
}

func SearchCurriculumTeacher(id int)(orm.Params,error) {
	o := orm.NewOrm()
	res := []orm.Params{}
	raw := o.Raw(`select
							t.nick_name,
							t.title,
							t.photo,
							t.curriculums,
							t.students,
							t.rating
						from tb_curriculum_teacher ct join tb_teacher t
						on ct.teacher_id=t.id
						where ct.curriculum_id=?`)
	_,err := raw.SetArgs(id).Values(&res)
	if err != nil {
		return nil, err
	}
	return res[0], nil
}

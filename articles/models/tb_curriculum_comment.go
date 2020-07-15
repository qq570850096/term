package models

import (
	"time"

	"github.com/astaxie/beego/orm"
)

type TbCurriculumComment struct {
	Id           int       `orm:"column(id);auto" json:"id"`
	CurriculumId uint      `orm:"column(curriculum_id)" description:"课程ID" json:"curriculum_id"`
	UserId       uint      `orm:"column(user_id)" description:"用户ID" json:"user_id"`
	Rating       uint8     `orm:"column(rating)" description:"评分" json:"rating"`
	Comment      string    `orm:"column(comment);size(200);null" description:"评语" json:"comment"`
	CreateTime   time.Time `orm:"column(create_time);type(timestamp);auto_now_add" description:"创建时间" json:"create_time"`
}

func (t *TbCurriculumComment) TableName() string {
	return "tb_curriculum_comment"
}

func init() {
	orm.RegisterModel(new(TbCurriculumComment))
}

// AddTbCurriculumComment insert a new TbCurriculumComment into database and returns
// last inserted Id on success.
func AddTbCurriculumComment(m *TbCurriculumComment) (id int64, err error) {
	o := orm.NewOrm()
	id, err = o.Insert(m)
	return
}

// GetTbCurriculumCommentById retrieves TbCurriculumComment by Id. Returns error if
// Id doesn't exist
func GetTbCurriculumCommentById(id int) (v *TbCurriculumComment, err error) {
	o := orm.NewOrm()
	v = &TbCurriculumComment{Id: id}
	if err = o.Read(v); err == nil {
		return v, nil
	}
	return nil, err
}

func SearchCurriComment(id int)([]orm.Params,error) {
	o:=orm.NewOrm()
	var res []orm.Params
	row := o.Raw(`select
						u.nick_name,
						cc.rating,
						cc.comment,
						u.photo
					from tb_curriculum_comment cc join tb_user u
					on cc.user_id = u.user_id
					where cc.curriculum_id=?
					order by cc.rating desc`)
	_,err := row.SetArgs(id).Values(&res)
	if err != nil {
		return nil,err
	}
	return res,err
}


package models

import (
	"github.com/astaxie/beego/orm"
)

type TbCurriculumIndex struct {
	Id           int    `orm:"column(id);auto" json:"id"`
	CurriculumId uint   `orm:"column(curriculum_id)" description:"课程ID" json:"curriculum_id"`
	SeqNum       uint   `orm:"column(seq_num)" description:"序号" json:"seq_num"`
	Chapter      string `orm:"column(chapter);size(200)" description:"章节标题" json:"chapter"`
	Section      string `orm:"column(section);size(1000);null" description:"课程小节" json:"section"`
}

func (t *TbCurriculumIndex) TableName() string {
	return "tb_curriculum_index"
}

func init() {
	orm.RegisterModel(new(TbCurriculumIndex))
}


func SearchPublicVideo(id int) (string,error) {
	o := orm.NewOrm()
	var res []orm.Params
	_,err := o.Raw(`select
				json_unquote(json_extract((section->'$[0]'),"$.video")) as public_video
				from tb_curriculum_index
				where curriculum_id=? and seq_num=1;`,id).Values(&res)
	if err != nil {
		return "" ,err
	}else {
		return res[0]["public_video"].(string),nil
	}
}

func SearchSummary(id int) (orm.Params,error) {
	o:=orm.NewOrm()
	res := []orm.Params{}
	raw := o.Raw(`select
							tc.title,
							tc.price,
							tc.current_price,
							s.buyers,
							s.comment,
							tc.video_num,
							tc.document_num
						from tb_curriculum tc join tb_curriculum_sales s
						on tc.id=s.curriculum_id
						where tc.id=?`)
	_,err := raw.SetArgs(id).Values(&res)
	if err != nil {
		return nil, err
	}
	return res[0],nil
}

func SearchCurriIndex(id int)([]orm.Params,error){
	o := orm.NewOrm()
	row := o.Raw(`select
							t.chapter,
							t.section
						from tb_curriculum_index t
						where curriculum_id=?`)
	res := []orm.Params{}
	_,err := row.SetArgs(id).Values(&res)
	if err != nil {
		return nil,err
	}
	return res,err
}

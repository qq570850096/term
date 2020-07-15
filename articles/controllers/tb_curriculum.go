package controllers

import (
	"articles/models"
	"articles/utils"
	"encoding/json"
	"fmt"
	"github.com/astaxie/beego"
	"github.com/astaxie/beego/orm"
)

// TbCurriculumController operations for TbCurriculum
type TbCurriculumController struct {
	beego.Controller
}

// URLMapping ...
func (c *TbCurriculumController) URLMapping() {
	c.Mapping("SearchTop",c.SearchTop)
	c.Mapping("SearchHot",c.SearchHot)
	c.Mapping("SearchRefined",c.SearchRefined)
	c.Mapping("SearchByPage",c.SearchByPage)
	c.Mapping("SearchCurriculum",c.SearchCurriculum)
}

// SearchByPage ...
// @Title SearchByPage
// @Description create TbCurriculum
// @Param	body		body 	utils.SearchForm	true		"body for SearchForm content"
// @Success 201 {int} utils.Resp
// @Failure 403 body is empty
// @router /searchByPage [post]
func (c *TbCurriculumController) SearchByPage() {
	data := c.Ctx.Input.RequestBody
	res := utils.Resp{}
	fmt.Println(data)
	// 对于json类型的请求需要这样获
	var search models.SearchForm
	json.Unmarshal(data,&search)
	fmt.Println(search)
	if tb,err := models.SearchByPage(search);err!=nil {
		c.Data["json"]=err.Error()
	} else {
		c.Ctx.Output.SetStatus(200)
		if tb == nil {
			res = utils.Resp{
				Msg:  "success",
				Result: make([]int,0),
			}
		}else {
			res = utils.Resp{
				Msg:  "success",
				Result: tb,
			}
		}

		c.Data["json"] = res
	}
	c.ServeJSON()
}

// GetHot ...
// @Title GetHot
// @Description create TbCurriculum
// @Param	body		body 	models.TbCurriculum	true		"body for TbCurriculum content"
// @Success 201 {int} models.TbCurriculum
// @Failure 403 body is empty
// @router /searchHot
func (c *TbCurriculumController) SearchHot() {
	if tb,err := models.SearchHot();err!=nil {
		c.Data["json"]=err.Error()
	} else {
		c.Ctx.Output.SetStatus(200)
		res := utils.Resp{
			Msg:  "success",
			Result: tb,
		}
		c.Data["json"] = res
	}
	c.ServeJSON()
}


// GetTop ...
// @Title GetTop
// @Description create TbCurriculum
// @Param	body		body 	models.TbCurriculum	true		"body for TbCurriculum content"
// @Success 201 {int} models.TbCurriculum
// @Failure 403 body is empty
// @router /searchtTop
func(c *TbCurriculumController) SearchTop(){
	if tb,err := models.SearchTop();err!=nil {
		c.Data["json"]=err.Error()
	} else {
		c.Ctx.Output.SetStatus(200)
		res := utils.Resp{
			Msg:  "success",
			Result: tb,
		}
		c.Data["json"] = res
	}
	c.ServeJSON()
}

// GetRefined ...
// @Title GetRefined
// @Description create TbCurriculum
// @Param	body		body 	models.TbCurriculum	true		"body for TbCurriculum content"
// @Success 201 {int} models.TbCurriculum
// @Failure 403 body is empty
// @router /searchRefined
func (c *TbCurriculumController) SearchRefined() {
	maps := make(map[string][]orm.Params)
	tb_1,err := models.SearchRefined("入门")
	tb_2,err := models.SearchRefined("简单")
	tb_3,err := models.SearchRefined("中等")
	maps["list_1"] = tb_1
	maps["list_2"] = tb_2
	maps["list_3"] = tb_3
	if err != nil {
		c.Data["json"] = err.Error()
	}
	c.Data["json"] = maps
	c.ServeJSON()
}

func (c *TbCurriculumController) SearchCurriculum() {
	data := c.Ctx.Input.RequestBody
	fmt.Println(data)
	maps := make(map[string]interface{})
	// 对于json类型的请求需要这样获
	var search models.SearchCurriculumForm
	json.Unmarshal(data,&search)
	fmt.Println(search)
	maps["summary"],_ = models.SearchSummary(search.Id)
	maps["publicVideo"],_ = models.SearchPublicVideo(search.Id)
	maps["teacher"],_ = models.SearchCurriculumTeacher(search.Id)
	maps["index"],_ = models.SearchCurriIndex(search.Id)
	maps["comment"],_ = models.SearchCurriComment(search.Id)
	c.Data["json"] = maps
	c.ServeJSON()
}
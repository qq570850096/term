// @APIVersion 1.0.0
// @Title beego Test API
// @Description beego has a very cool tools to autogenerate documents for your API
// @Contact astaxie@gmail.com
// @TermsOfServiceUrl http://beego.me/
// @License Apache 2.0
// @LicenseUrl http://www.apache.org/licenses/LICENSE-2.0.html
package routers

import (
	"articles/controllers"

	"github.com/astaxie/beego"
)

func init() {
	ns := beego.NewNamespace("renren-fast/app",

		beego.NSNamespace("/curriculum",
			beego.NSInclude(
				&controllers.TbCurriculumController{},
			),
		),

		beego.NSNamespace("/curriculum_comment",
			beego.NSInclude(
				&controllers.TbCurriculumCommentController{},
			),
		),

		beego.NSNamespace("/curriculum_document",
			beego.NSInclude(
				&controllers.TbCurriculumDocumentController{},
			),
		),

		beego.NSNamespace("/curriculum_index",
			beego.NSInclude(
				&controllers.TbCurriculumIndexController{},
			),
		),

		beego.NSNamespace("/curriculum_sales",
			beego.NSInclude(
				&controllers.TbCurriculumSalesController{},
			),
		),

		beego.NSNamespace("/curriculum_teacher",
			beego.NSInclude(
				&controllers.TbCurriculumTeacherController{},
			),
		),

		beego.NSNamespace("/curriculum_type",
			beego.NSInclude(
				&controllers.TbCurriculumTypeController{},
			),
		),

		beego.NSNamespace("/learning",
			beego.NSInclude(
				&controllers.TbLearningController{},
			),
		),

		beego.NSNamespace("/order",
			beego.NSInclude(
				&controllers.TbOrderController{},
			),
		),

		beego.NSNamespace("/order_temp",
			beego.NSInclude(
				&controllers.TbOrderTempController{},
			),
		),

		beego.NSNamespace("/teacher",
			beego.NSInclude(
				&controllers.TbTeacherController{},
			),
		),

		beego.NSNamespace("/user",
			beego.NSInclude(
				&controllers.TbUserController{},
			),
		),
	)
	beego.AddNamespace(ns)
}

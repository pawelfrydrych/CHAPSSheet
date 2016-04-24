package chapssheet

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/rehearsal/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
        "/api/memberReharsal"(controller: "memberRehearsalHistory", action: [POST: "save", OPTIONS: 'save'])
        "/api/rehearsals"(controller: "rehearsal", action: [POST: "save",GET:"index", OPTIONS: 'save'])

    }
}

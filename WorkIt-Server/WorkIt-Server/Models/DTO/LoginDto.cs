using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models.DTO
{
    public class LoginDTO
    {
        public string Email { get; set; }
        public string PassHash { get; set; }
    }
}
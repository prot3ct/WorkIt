﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models.DTO
{
    public class JobReportDTO
    {
        public string Description { get; set; }
        public int UserId { get; set; }
        public int JobId { get; set; }
    }
}
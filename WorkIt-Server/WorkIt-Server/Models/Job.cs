﻿using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.Models
{
    public class Job
    {
        [Key]
        public int JobId { get; set; }
        public string Title { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime EndDate { get; set; }
        public string Description { get; set; }
        public string Reward { get; set; }
        public int MinRaiting { get; set; }
        public int MinJobsCompleted { get; set; }
        public bool IsCompleted { get; set; }

        public int CreatorId { get; set; }
        public User Creator { get; set; }
        public int PlaceId { get; set; }
        public Place Place { get; set; }
    }
}
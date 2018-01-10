﻿using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models
{
    public class Place
    {
        [Key]
        public int PlaceId { get; set; }

        [Required]
        [Index("IX_CountryCityAndAddress", 1, IsUnique = true)]
        [MaxLength(50)]
        public string Country { get; set; }

        [Required]
        [Index("IX_CountryCityAndAddress", 2, IsUnique = true)]
        [MaxLength(50)]
        public string City { get; set; }

        [Required]
        [Index("IX_CountryCityAndAddress", 3, IsUnique = true)]
        [MaxLength(50)]
        public string Address { get; set; }
    }
}
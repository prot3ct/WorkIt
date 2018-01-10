﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WorkIt_Server.Models;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.BLL.Extensions
{
    public static class JobDTOExtensions
    {
        public static Job ToJob(this JobDTO jobDTO, UserBussinessLogic userLogic, PlaceBussinessLogic placeLogic)
        {
            var job = new Job {
                MinJobsCompleted = jobDTO.MinJobsCompleted,
                MinRaiting = jobDTO.MinRaiting,
                Reward = jobDTO.Reward,
                Creator = userLogic.GetUserByEmail(jobDTO.CreatorEmail),
                Description = jobDTO.Description,
                StartDate = jobDTO.StartDate,
                EndDate = jobDTO.EndDate,
                Place = jobDTO.ToPlace(),
                Title = jobDTO.Title,
            };
            //var place = jobDTO.ToPlace();

            //if (placeLogic.CheckExsistingPlace(place))
            //{
            //    job. = placeLogic.GetPlaceId(place);
            //}
            //job.Place = 

            return job;
        }
        public static Place ToPlace(this JobDTO jobDTO)
        {
            var place = new Place
            {
                City = jobDTO.City,
                Country = jobDTO.Country,
                Address = jobDTO.Address
            };

            return place;
        }
    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Security.Claims;
using System.Threading;
using System.Web;
using WorkIt_Server.Models.Context;

namespace WorkIt_Server.Handlers
{
    public class ApplicationAuthenticationHandler : DelegatingHandler
    {
        private WorkItDbContext db = new WorkItDbContext();
        private const string InvalidToken = "Invalid Authorization-Token";
        private const string MissingToken = "Missing Authorization-Token";

        protected override System.Threading.Tasks.Task<HttpResponseMessage> SendAsync(HttpRequestMessage
 request, System.Threading.CancellationToken cancellationToken)
        {
            IEnumerable<string> sampleApiKeyHeaderValues = null;

            if (request.Headers.TryGetValues("authToken", out sampleApiKeyHeaderValues))
            {
                string[] apiKeyHeaderValue = sampleApiKeyHeaderValues.First().Split(':');

                if (apiKeyHeaderValue.Length == 2)
                {
                    if (!int.TryParse(apiKeyHeaderValue[0], out int userId))
                    {
                        return requestCancel(request, cancellationToken, InvalidToken);
                    }


                    var accessToken = apiKeyHeaderValue[1];

                    var realAccessToken = db.Users.FirstOrDefault(u => u.UserId == userId).AccessToken;

                    if (realAccessToken.Equals(accessToken))
                    {
                        var userNameClaim = new Claim(ClaimTypes.Name, accessToken);
                        var identity = new ClaimsIdentity(new[] { userNameClaim }, "SampleAppApiKey");
                        var principal = new ClaimsPrincipal(identity);
                        Thread.CurrentPrincipal = principal;

                        if (System.Web.HttpContext.Current != null)
                        {
                            System.Web.HttpContext.Current.User = principal;
                        }
                    }

                    else
                    {
                        return requestCancel(request, cancellationToken, InvalidToken);
                    }
                }
                else
                {
                    return requestCancel(request, cancellationToken, MissingToken);

                }
            }
            else
            {
                return requestCancel(request, cancellationToken, MissingToken);
            }

            return base.SendAsync(request, cancellationToken);
        }

        private System.Threading.Tasks.Task<HttpResponseMessage> requestCancel(HttpRequestMessage
request, System.Threading.CancellationToken cancellationToken, string message)

        {

            CancellationTokenSource _tokenSource = new CancellationTokenSource();

            cancellationToken = _tokenSource.Token;

            _tokenSource.Cancel();

            HttpResponseMessage response = new HttpResponseMessage();



            response = request.CreateResponse(HttpStatusCode.BadRequest);

            response.Content = new StringContent(message);

            return base.SendAsync(request, cancellationToken).ContinueWith(task =>

            {

                return response;

            });

        }

    }
}
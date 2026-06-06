import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // Define methods that require authentication
  const writeMethods = ['POST', 'PUT', 'DELETE'];

  if (writeMethods.includes(req.method)) {
    // Attach Basic Auth header (Base64 encoded admin:password)
    const authHeader = 'Basic ' + btoa('user:7548545e-4c74-427c-99d7-846399435422'); // Replace with your generated Spring password if not configured
    
    const authReq = req.clone({
      setHeaders: {
        Authorization: authHeader
      }
    });
    return next(authReq);
  }

  // Pass GET requests through without modification
  return next(req);
};
